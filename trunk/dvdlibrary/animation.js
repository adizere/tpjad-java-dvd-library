function ActivateFormInputBgColor(Input, StyleClass)
{
	with (Input)
	{
		document.getElementsByName(name).item(0).className += " "+StyleClass;
	}
}

function DeactivateFormInputBgColor(Input, StyleClass)
{
	with (Input)
	{
		var CrtClassName = document.getElementsByName(name).item(0).className;
		var NewClassName = CrtClassName.replace (" "+StyleClass, "");
		document.getElementsByName(name).item(0).className = NewClassName;
	}
}