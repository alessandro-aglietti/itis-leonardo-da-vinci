$(document).ready(function(){
	loadTaccuini();
});


var taccuinoApiPath = "/taccuino";
	
var taccuini = {};

function showLoading() {
	$("#loading-overlay").stop().fadeIn();
}

function hideLoading() {
	$("#loading-overlay").stop().fadeOut();
}






function loadTaccuini() {
	var $container = $("#taccuini-container");
	
	showLoading();
	
	var $promise = $.get(taccuinoApiPath);
	$promise.always(function(){
		hideLoading()
	});
	$promise.success(function(retrievedTaccuini){
		$container.empty();
		$.each(retrievedTaccuini,function(index,taccuino){
			taccuini[taccuino.id] = taccuino;
			renderTaccuino(taccuino,$container);
		});
	});
	$promise.error(function(){
		alert("Si è verificato un errore durante il caricamento della lista dei taccuini...ricarica la pagina");
	});
}

function newTaccuino(titolo) {
	if (typeof titolo === "undefined") titolo = prompt("Che nome vuoi dare al nuovo taccuino?");
	if (titolo !== null) {
		
		var newTaccuino = {
			titolo: titolo
		}
		saveTaccuino(newTaccuino);

	}
}

function editTaccuino(taccuinoId) {
	var taccuino = taccuini[taccuinoId];
	var newTitle = prompt("Modifica il contenuto di questa nota",taccuino.titolo);
	if (newTitle !== null) {
		taccuino.titolo = newTitle;
		saveTaccuino(taccuinoId);
	}
}

function removeTaccuino(taccuinoId) {
	var $promise = $.ajax({
		method: "DELETE",
		url: taccuinoApiPath+"/"+taccuinoId,
	});
	$promise.always(function(){
		hideLoading();
	});
	$promise.success(function(){
		delete taccuini[taccuinoId];
		$("#taccuini-container").find('[data-taccuino-id="'+taccuinoId+'"]').remove();
	});
	$promise.error(function(){
		alert("Si è verificato un errore durante l'eliminazione dal taccuino... riprova");
	});
}

function saveTaccuino(taccuinoId_or_taccuino) {
	showLoading();
	
	var taccuino = typeof taccuinoId_or_taccuino === "object" ? taccuinoId_or_taccuino : taccuini[taccuinoId_or_taccuino];

	var $promise = $.ajax({
		method: "POST",
		url: taccuinoApiPath+"/"+ (taccuino.id ? taccuino.id : ""),
		data: JSON.stringify(taccuino),
		contentType: "application/json; charset=utf-8",
		dataType: "json",
	});
	$promise.always(function(){
		hideLoading();
	});
	$promise.success(function(retrievedTaccuino){
		taccuini[retrievedTaccuino.id] = retrievedTaccuino;
		renderTaccuino(retrievedTaccuino);
	});
	$promise.error(function(){
		alert("Si è verificato un errore durante il salvataggio dal taccuino... riprova");
	});
	return $promise;
}


function renderTaccuino(taccuino,$container) {
	if (typeof $container === "undefined") $container = $("#taccuini-container");
		
	// taccuino
	var $taccuinoNode = $('<div class="panel panel-info taccuino" data-taccuino-id="'+taccuino.id+'"><div class="panel-heading"></div><div class="panel-body"><ul class="list-unstyled note-container"></ul></div><div class="panel-footer"></div></div>')
	
	// titolo
	var $taccuinoHeaderNode = $taccuinoNode.find('.panel-heading');
	$taccuinoHeaderNode.html(taccuino.titolo ? taccuino.titolo : "&nbsp;");
	$taccuinoHeaderNode.append("<a href='javascript:editTaccuino(\""+taccuino.id+"\")' class='btn btn-primary btn-xs pull-right'><span class='glyphicon glyphicon-pencil'></span> modifica</a>");
	
	// note		
	if (typeof taccuino.note !== "undefined") {
		var $taccuinoNoteListNode = $taccuinoNode.find('ul');
		$.each(taccuino.note,function(index,nota){
			var $taccuinoNoteListItemNode = $('<li class="well nota">'+nota.testo+'<hr><div class="nota-footer"></div></li>');
			
			var $taccuinoNoteListItemFooterNode = $taccuinoNoteListItemNode.find('.nota-footer');
			$taccuinoNoteListItemFooterNode.append("<a href='javascript:editNota(\""+taccuino.id+"\","+index+")' class='btn btn-xs btn-primary'><span class='glyphicon glyphicon-pencil'></span></a>&nbsp");
			$taccuinoNoteListItemFooterNode.append("<a href='javascript:removeNota(\""+taccuino.id+"\","+index+")' class='btn btn-xs btn-danger'><span class='glyphicon glyphicon-trash'></span></a>");
			
			$taccuinoNoteListNode.append($taccuinoNoteListItemNode);
			
		});
	}
	
	// footer
	var $taccuinoFooterNode = $taccuinoNode.find('.panel-footer');
	$taccuinoFooterNode.append("<a href='javascript:newNota(\""+taccuino.id+"\")' class='btn btn-primary'><span class='glyphicon glyphicon-plus'></span> Nuova nota</a>");
	$taccuinoFooterNode.append("<a href='javascript:removeTaccuino(\""+taccuino.id+"\")' class='btn btn-danger pull-left'><span class='glyphicon glyphicon-remove'></span> Elimina taccuino</a>");
	
	// controllo se il taccuino è già stato renderizzato. In caso affermativo, lo sostituisco, altrimenti appendo.
	var $alreadyRenderedTaccuinoNode = $container.find('[data-taccuino-id="'+taccuino.id+'"]')
	if ($alreadyRenderedTaccuinoNode.size()>0) 
		$alreadyRenderedTaccuinoNode.replaceWith($taccuinoNode);
	else
		$container.append($taccuinoNode);
}





function newNota(taccuinoId,content) {
	if (typeof content === "undefined") content = prompt("Inserisci il testo della nota");
	if (content !== null) {
		showLoading();

		var taccuino = taccuini[taccuinoId];
		if (typeof taccuino.note === "undefined") taccuino.note = [];
		var newNota = {
			testo: content
		}
		taccuino.note.push(newNota);
		
		saveTaccuino(taccuinoId);
	}
}

function editNota(taccuinoId,notaIndex) {
	var taccuino = taccuini[taccuinoId];
	var newContent = prompt("Modifica il contenuto di questa nota",taccuino.note[notaIndex].testo);
	if (newContent !== null) {
		taccuino.note[notaIndex].testo = newContent;
		saveTaccuino(taccuinoId);
	}
}

function removeNota(taccuinoId,notaIndex) {

	// salva per eventuale rollback
	var notaToBeRemoved =  taccuini[taccuinoId].note.splice(notaIndex,1)[0];

	showLoading();

	var $promise = saveTaccuino(taccuinoId);
	$promise.error(function(){
		// rollback
		taccuini[taccuinoId].note.splice(notaIndex,0,notaToBeRemoved);
	});
}