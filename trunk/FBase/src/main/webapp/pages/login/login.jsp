<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<body>
<head>
 	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>FBase</title>
	<link href="styles/basic.css" rel="stylesheet" type="text/css" />
	<link rel="shortcut icon" href="styles/images/favicon.ico" />
</head>

<div id ="login-box">
		
	<s:form action="login"  > 
		<s:textfield name="username" label="Enter Username" /><br>
		<s:password name="password" label="Enter Password" /><br>
		<s:submit value="Login" align="center" /> 
	</s:form>
	
	 <s:if test="hasActionErrors()">
	   <div id="login-errors">
	      <s:actionerror/>
	   </div>
	</s:if>
</div>
</body>
</html>	