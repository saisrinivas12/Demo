<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
    <title> pATIENTS</title>
</head>
<body>
    <table border="1" cellspacing="1">
        <th>patientId</th>
        <th> patientName</th>
        <c:forEach items="${listPatients}" var="record">
            <tr>
                <input type ="checkbox" id ="patientCheck" name ="patientCheck">
                <td id ="patientId" name ="patientId"><c:out value ="${record.patientId}"></c:out></td>
                <td id ="patientName" name ="patientName"><c:out value ="${record.patientName}"></c:out></td>
                
            </tr>
        </c:forEach>
    </table>
    <div id ="error">

    </div>
    <input type ="button" value ="submit" id ="btn" name ="btn">
</body>
    </html>
    <script>
        var flag=false;
        var patientId ="";
        var patientName ="";
       $("button").click(()=>{
         var c = document.getElementsByName("patientCheck");
         var d = document.getElementsByName("patientId");
         
         var e = document.getElementsByClassName("patientName"); 
           for(var i=0;i<c.length;i++){
              if(c[i].nodeValue ==="true"){
                flag = true;
                patientId+=d[i].nodeValue.toString()+" ";
                patientName+= e[i].nodeValue.toString()+" ";
              }
           }
            if(flag === false){
              $("div").append("<p>please select atleast one checkbox</p>");
              return false;
            }

            $.ajax({
                method:"POST",
                contentType:"application/json",
                url:"/deletePatient?patientId="+patientId+"?patientName"+patientName,
                success:function(response){
                      $("div").append("<p> deleted successfully</p>")
                }
            });
       });


    </script>
