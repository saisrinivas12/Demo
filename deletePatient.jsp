<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
    <body>
        <form action="/deletePatientInfo" method="POST" modelAttribute="deletePatient">
            <label for="patientId">Patient id</label>
            <input type="text" id="patientId" name="patientId">
        </form>
        <c:out value="${result}"></c:out>
        <c:if test="${result =='deleted successfully'}">
           <h1><c:out value="${result}"></c:out></h1>
        </c:if>
    </body>
</html>
