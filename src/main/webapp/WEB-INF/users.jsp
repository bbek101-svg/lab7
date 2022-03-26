<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Users</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

    </head>
    <body>
        
        <div class="container">
            <h1 class="text-center">User Management System</h1>
            <br>
            <div class="row">
                <div class ="col-3">
                    <h2>Add an User</h2>
                    <form action="user" method="POST">
                        <input type="hidden" name="action" value="add">
                        <input type="text" class="form-control" placeholder="Email" name="email">
                        <input type="text" class="form-control" placeholder="First Name" name="fname">
                        <input type="text" class="form-control" placeholder="Last Name" name="lname">
                        <input type="password" class="form-control" placeholder="Password" name="password">
                        <br>Active <input type ="checkbox" class="form-check-input" name="active" value="active">
                        <br><select name="role">
                            <option value="system admin">system admin</option>
                            <option value="regular user">regular user</option>
                            <option value="company admin">company admin</option>
                        </select>
                        <br>
                        <br>
                        <button class="btn btn-dark" type ="submit" value="Add">Add</button>
                        
                    </form>
                    <p>${q1}</p>
                </div>
                     
                <div class="col-6">
                    <table class="table table-dark table-striped">
                        <thead>
                            <tr>
                                <th>E-mail</th>
                                <th>First Name</th>
                                <th>Last Name</th>
                                <th>Role</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${users}" var="user">
                                <tr>
                                    <td>${user.getEmail()}</td>
                                    <td>${user.firstName}</td>
                                    <td>${user.lastName}</td>
                                    <td>${user.getRole().getRoleName()}</td>
                                    <td>
                                    <a href="user?edit=${user.email}">Edit</a>
                                     <a href="user?delete=${user.email}">Delete</a>
                                    </td>
                                </tr>
                    
                            </c:forEach>
                        </tbody>
                    </table>
                    
                </div>
                <div class ="col-3">
                    <h2>Update an User</h2>
                    <form action="user" method="POST">
                        <input type="hidden" name="action" value="update">
                        <input type="hidden" name="email" value="${email}">
                               
                        <input type="text" class="form-control" placeholder="First Name" name="fname" value="${fname}">
                        <input type="text" class="form-control" placeholder="Last Name" name="lname" value="${lname}">
                        <input type="password" class="form-control" placeholder="Password" name="password" value="${password}">
                        
                        <br><select name="role">
                            
                            <option value="system admin">system admin</option>
                            
                            <option value="regular user">regular user</option>
                          
                            <option value="company admin">company admin</option>
                        </select>
                        <br>
                        <br>
                        <button class="btn btn-dark" type ="submit">Edit</button>
                    </form>
                </div>
                
            </div>
                        
        </div>
        
    </body>
</html>
