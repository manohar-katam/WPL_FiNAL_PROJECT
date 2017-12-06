


<%--
  Created by IntelliJ IDEA.
  User: manohar
  Date: 01-12-2017
  Time: 13:51
  To change this template use File | Settings | File Templates.
--%>
<%@include file="_user-header.jsp"%>

<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.16/css/jquery.dataTables.css">
<script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.10.16/js/jquery.dataTables.js"></script>

<section id="products">
<div class="container">
    <div class="col-lg-12">
    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">Filter</button>

        <div class="row">
            <table id="productTable" class="table">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">Product ID</th>
                    <th scope="col">Name</th>
                    <th scope="col">Price</th>
                    <th scope="col">Category</th>
                    <th scope="col">Color</th>
                    <th scope="col">Rating</th>
                    <th scope="col">ADD</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${inventoryList}" var="inventory">
                    <tr>
                        <td><c:out value="${inventory.getId()}" /></td>
                        <td><c:out value="${inventory.getName()}" /></td>
                        <td><c:out value="${inventory.getPrice()}" /></td>
                        <td><c:out value="${inventory.getCategory()}" /></td>
                        <td><c:out value="${inventory.getColor()}" /></td>
                        <td><c:out value="${inventory.getRating()}" /></td>
                        <td><a href="/registry/createRegistry/addProduct?productId=${inventory.getId()}"><button class="btn btn-primary text-uppercase" type="submit">ADD</button></a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            </div>
        </div>
</div>
    <div class="container">
        <form action="/createRegistry/registryCart" method="POST">
            <div class="form-group">
                <button id="createReg" type="submit" class="btn btn-info btn-block">Go to your Registry Cart</button>
            </div>
        </form>
    </div>

    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Filter</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form:form method="POST" id="filter" action="/filter" modelAttribute="filter">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="category_id"> Category</label>
                                    <form:select class="form-control" name="category_id" path="category">
                                        <option value="0">NONE</option>
                                        <option value="1">Electronics</option>
                                        <option value="2">Home</option>
                                        <option value="3">Kitchen</option>
                                        <option value="4">Clothing</option>
                                    </form:select>
                                    <p class="help-block text-danger"></p>
                                </div>
                                <div class="form-group">
                                    <label for="price">Price below</label>
                                    <form:input class="form-control" id="price" path="price" type="text" placeholder="Price *"/>
                                    <p class="help-block text-danger"></p>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="color"> Color</label>
                                    <form:select class="form-control" name="color" path="color">
                                        <option value="0">NONE</option>
                                        <option value="1">Yellow</option>
                                        <option value="2">Green</option>
                                        <option value="3">Red</option>
                                        <option value="4">Blue</option>
                                        <option value="5">Pink</option>
                                        <option value="6">Black</option>
                                        <option value="7">White</option>
                                    </form:select>
                                    <p class="help-block text-danger"></p>
                                </div>
                                <div class="form-group">
                                    <label for="rating">Rating</label>
                                    <form:select class="form-control" name="rating" path="rating">
                                        <option value="0">NONE</option>
                                        <option value="1">1</option>
                                        <option value="2">2</option>
                                        <option value="3">3</option>
                                        <option value="4">4</option>
                                        <option value="5">5</option>
                                    </form:select>
                                    <p class="help-block text-danger"></p>
                                </div>
                            </div>
                            <div class="clearfix"></div>
                            <div class="col-lg-12 text-center">
                                <div id="success"></div>
                                <button id="sendMessageButton" class="btn btn-primary btn-lg text-uppercase" type="submit">FILTER</button>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>







</section>
<!--<aside>
    <div id="col-md-3" style="float: left;">
        <div class="list-group panel">
            <a class="list-group-registryItem list-group-registryItem strong text-center" style="background: #009ce0; color: white;" data-toggle="collapse"> Personalize Your Search</a>
            <a href="#demo1" class="list-group-registryItem list-group-registryItem-success strong" style="background: #f7f7f7;" data-toggle="collapse" data-parent="#MainMenu"><i class="fa fa-photo"></i> Resource Type <i class="fa fa-caret-down"></i></a>
            <div class="collapse list-group-submenu" id="demo1">
                <a href="#" class="list-group-registryItem"><input type="checkbox">  Audio Content</a>
                <a href="#" class="list-group-registryItem"><input type="checkbox">  Interactive Media</a>
                <a href="#" class="list-group-registryItem"><input type="checkbox">  Learning Game</a>
                <a href="#" class="list-group-registryItem"><input type="checkbox">  Video</a>
            </div>
            <a href="#demo2" class="list-group-registryItem list-group-registryItem strong" style="background: #f7f7f7;" data-toggle="collapse" data-parent="#MainMenu"><i class="fa fa-book"></i> Readability <i class="fa fa-caret-down"></i></a>
            <div class="collapse list-group-submenu" id="demo2">
                <a href="#" class="list-group-registryItem"><input type="checkbox">  1</a>
                <a href="#" class="list-group-registryItem"><input type="checkbox">  2</a>
                <a href="#" class="list-group-registryItem"><input type="checkbox">  3</a>
                <a href="#" class="list-group-registryItem"><input type="checkbox">  4</a>
                <a href="#" class="list-group-registryItem"><input type="checkbox">  5</a>
            </div>
            <a href="#demo4" class="list-group-registryItem list-group-registryItem strong" style="background: #f7f7f7;" data-toggle="collapse" data-parent="#MainMenu"><i class="fa fa-language"></i> Languages <i class="fa fa-caret-down"></i></a>
            <div class="collapse list-group-submenu" id="demo4">
                <a href="#" class="list-group-registryItem"><input type="checkbox">  English</a>
                <a href="#" class="list-group-registryItem"><input type="checkbox">  Chinese</a>
                <a href="#" class="list-group-registryItem"><input type="checkbox">  French</a>
                <a href="#SubSubMenu1" class="list-group-registryItem strong" data-toggle="collapse" data-parent="#SubSubMenu1"> 3 more <i class="fa fa-caret-down"></i></a>
                <div class="collapse list-group-submenu list-group-submenu-1" id="SubSubMenu1">
                    <a href="#" class="list-group-registryItem"><input type="checkbox">  Russian</a>
                    <a href="#" class="list-group-registryItem"><input type="checkbox">  Urdu</a>
                    <a href="#" class="list-group-registryItem"><input type="checkbox">  Vietnamese</a>
                </div>
            </div>
            <a href="#demo5" class="list-group-registryItem list-group-registryItem strong" style="background: #f7f7f7;" data-toggle="collapse" data-parent="#MainMenu"><i class="fa fa-cubes"></i>  Content Collections <i class="fa fa-caret-down"></i></a>
            <div class="collapse list-group-submenu" id="demo5">
                <a href="#" class="list-group-registryItem"><input type="checkbox">  ARKive</a>
                <a href="#" class="list-group-registryItem"><input type="checkbox">  BrainPOP Junior</a>
                <a href="#" class="list-group-registryItem"><input type="checkbox">  CK-12</a>
                <a href="#" class="list-group-registryItem"><input type="checkbox">  Khan Academy</a>
                <a href="#" class="list-group-registryItem"><input type="checkbox">  Library of Congress</a>
                <a href="#" class="list-group-registryItem"><input type="checkbox">  NCTM Illuminations</a>
                <a href="#" class="list-group-registryItem"><input type="checkbox">  PBS</a>
                <a href="#" class="list-group-registryItem"><input type="checkbox">  Teach Engineering</a>
            </div>
            <a class="list-group-registryItem list-group-registryItem strong text-center" style="background: #009ce0; color: white;" data-toggle="collapse"><button type="button" class="btn btn-success btn-sm">SEARCH</button> </a>
        </div>
    </div>
</aside>-->

<script type="text/javascript">
    jQuery(document).ready(function ($) {
        $('#productTable').DataTable();

    })
</script>
<!-- cart -->

<%@include file="_footer.jsp"%>
