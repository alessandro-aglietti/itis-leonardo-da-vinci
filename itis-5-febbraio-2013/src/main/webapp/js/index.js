function onLoad(){
	console.log("onLoad()");
	
	$("#menu a").click(menuClickHadler);
}

function menuClickHadler(eventObj){
	
	resetMenuStyles();
	
	// aggiungo la classe al contenitore dell'ancora
	$(this).parent().addClass("active");
	
	// aggiungo la classe per invertire il colore dell'icona
	$(this).children().addClass("icon-white");
}

function resetMenuStyles(){
	// rimuovo la classe al contenitore dell'ancora
	$("#menu li").removeClass("active");
	
	// rimuovo la classe per invertire il colore dell'icona
	$("#menu i").removeClass("icon-white");
}