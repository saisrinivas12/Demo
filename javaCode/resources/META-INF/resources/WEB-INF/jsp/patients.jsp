<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<body>
    <div>
        <table border="1" cellspacing ="5" cellpadding="5">
            <th>patient Id</th>
            <th>patientName</th>
            <th>patient date of dateOfRegitstration</th>
            <c:forEach items="${listPatients}" var="record">
                <tr>
                <td><c:out value ="${record.patientId}"></c:out></td>
                <td><c:out value ="${record.patientName}"></c:out></td>
                <td><c:out value ="${record.dateOfRegitstration}"></c:out></td>
            </tr>
            </c:forEach>
</table>    </div>
</body>
</html>