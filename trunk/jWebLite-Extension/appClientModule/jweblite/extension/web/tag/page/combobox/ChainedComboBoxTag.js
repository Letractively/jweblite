/* $1=eid, $2=toEid, $3=map */
var $1Map = $3;
var $1Func = function(callback) {
	var obj = document.getElementById("$1");
	var toEle = document.getElementById("$2");
	if (obj != null && obj.options != null && toEle != null && toEle.options != null) {
		toEle.options.length = 0;
		var toEleArray = null;
		if ($1Map != null && obj.options.length > 0 && (toEleArray = $1Map[obj.options[obj.selectedIndex].value]) != null) {
			for ( var i in toEleArray) {
				var toEleValue = toEleArray[i];
				if (toEleValue == null) {
					continue;
				}
				var toEleOpt = document.createElement("option");
				if (typeof (toEleValue) == "object") {
					toEleOpt.innerHTML = toEleValue.name;
					toEleOpt.value = toEleValue.value;
				} else {
					toEleOpt.innerHTML = toEleValue;
				}
				toEle.appendChild(toEleOpt);
			}
		}
		if (callback) {
			callback();
		}
	}
};
if (typeof ($1ParentFunc) == 'function') {
	$1ParentFunc();
};
var $2ParentFunc = $1Func;