function formatPhone(field1) {//Formata o valor recebido JQuery caso tenha 8 dígitos (XX)XXXXX-XXXX, (XX)XXXX-XXXX
	if ($(field1).value.length == 13) { //Formata para padrão de 8 dígitos
		var inValue = $(field1).value;
		var outValue = ""; 
		for (var i=0; i<inValue.length;i++){
			if (inValue.charAt(i) != '-') {
				if (outValue.length == 8)
					outValue=outValue+"-"+inValue.charAt(i);
				else
					outValue=outValue+inValue.charAt(i);
			}
		}
		$(field1).value = outValue;
	}
}