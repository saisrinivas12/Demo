<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <title>Patient Registration Page</title>

   
    <body>
        <h1><b>Patient Registration Form</b></h1>
        <div id ="form">
        <form action="/RegisterPatient" method="post" modelAttribute ="Patient" onsubmit="return validateForm()" id="form1">
            <label for="patientId">patientId: </label>
            <input type="text" id="patientId" name="patientId"><br><br>
            <label for="patientName">patientName:</label>
            <input type="text" id="patientName" name="patientName"><br><br>
        
            <label for="dateOfRegitstration">Date of Registration:</label>
            <input type="date" id="dateOfRegitstration" name="dateOfRegitstration">
            <br/>
            <label for="btn"></label>
            <input type="submit" value="Submit" id ="btn">
          </form>
          <br/>
          <p> if you are existing user please login<a href="/login">login</a></p>
        </div>
        <div id ="error">

        </div>
    <c:out value="${result}"></c:out>
<c:if test="${not empty result  && result =='you are currently logged in'}">
   
    <aside>operations you wanna do on patientinfo <span><a href="/updatePatientInfo">updatepatient</a></span>
    <span><a href="/deletePatient">deletePatient</a></span>
    <span><a href="/">register</a></span>
    <span><a href="/getPatients">getPatients</a></span>
</c:if>
    </aside>
</body>
</html>
<script>
    function validateForm(){
        var patientId = document.getElementById("patientId");
        var patientName = document.getElementById("patientName");
        var dateOfRegistration = document.getElementById("dateOfRegitstration");
        if( patientId.value ==="" || patientName.value==="" || dateOfRegistration.value===""){
         document.getElementById("error").append("please enter all values in order to continue registering an account");
         return false;
        }
        return true;
    }
</script>
