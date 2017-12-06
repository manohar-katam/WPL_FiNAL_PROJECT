<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: manohar
  Date: 01-12-2017
  Time: 13:56
  To change this template use File | Settings | File Templates.
--%>

<%@include file="_admin-header.jsp"%>

<section id="add_product">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 text-center">
                <h2 class="section-heading text-uppercase">Add a product to inventory</h2>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
            <form:form method="POST" id="productform" action="inventory" modelAttribute="inventory">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="name">Product Name</label>
                                <form:input class="form-control" id="name" name="name" path="name" type="text" placeholder="Product Name *"/>
                                <p class="help-block text-danger"></p>
                            </div>
                            <div class="form-group">
                                <label for="category_id"> Category</label>
                                <form:select class="form-control" name="category_id" path="category_id">
                                    <option value="1">Electronics</option>
                                    <option value="2">Home</option>
                                    <option value="3">Kitchen</option>
                                    <option value="4">Clothing</option>
                                </form:select>
                                <p class="help-block text-danger"></p>
                            </div>
                            <div class="form-group">
                                <label for="price">Price</label>
                                <form:input class="form-control" id="price" path="price" type="text" placeholder="Price *"/>
                                <p class="help-block text-danger"></p>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="color"> Color</label>
                                <form:select class="form-control" name="color" path="color">
                                    <option value="1">Yellow</option>
                                    <option value="2">Green</option>
                                    <option value="3">Red</option>
                                    <option value="4">Blue</option>
                                    <option value="4">Pink</option>
                                    <option value="4">Black</option>
                                    <option value="4">White</option>
                                </form:select>
                                <p class="help-block text-danger"></p>
                            </div>
                            <div class="form-group">
                                <label for="rating">Rating</label>
                                <form:select class="form-control" name="rating" path="rating">
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
                            <button id="sendMessageButton" class="btn btn-primary btn-lg text-uppercase" type="submit">ADD PRODUCT</button>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</section>

<%@include file="_footer.jsp"%>
