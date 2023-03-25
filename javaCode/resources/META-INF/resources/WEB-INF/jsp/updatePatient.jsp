<html>
    <body>
        <form action="/updatePatient" method="post" modelAttribute ="updatePatient">
            <label for="patientId">patientId </label>
            <input type="text" id="patientId" name="patientId"><br><br>
            <label for="patientName">patientName</label>
            <input type="text" id="patientName" name="patientName"><br><br>
            <label for="dateOfRegitstration">Date of Registration</label>
            <input type="date" id="dateOfRegitstration" name="dateOfRegitstration">
            <input type="submit" value="Submit">
          </form>
    </body>
</html>