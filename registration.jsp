<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
    <title>Patient Registration Page</title>
    <body>
        <form action="/RegisterPatient" method="post" modelAttribute ="Patient">
            <label for="patientId">patientId </label>
            <input type="text" id="patientId" name="patientId"><br><br>
            <label for="patientName">patientName</label>
            <input type="text" id="patientName" name="patientName"><br><br>
            <label for="dateOfRegitstration">Date of Registration</label>
            <input type="date" id="dateOfRegitstration" name="dateOfRegitstration">
            <input type="submit" value="Submit">
          </form>
          <h1>hi</h1>
    <c:out value="${result}"></c:out>
    
<c:if test="${not empty result  && result =='you are currently logged in'}">
    <p>link to get list of employees Registered<a href="/getPatients">getPatients</a></p>
    </body>
    <aside>operations you wanna do on patientinfo <span><a href="/updatePatientInfo">updatepatient</a></span>
    <span><a href="/deletePatient">deletePatient</a></span>
    <span><a href="/">register</a></span>
    <span><a href="/getPatients">getPatients</a></span>
</c:if>
    <p> if you are existing user please login<a href="/login">login</a></p>
    </aside>
     
</html>
<script>
    function Function1(){
        alert("clicked");
    }
</script>
