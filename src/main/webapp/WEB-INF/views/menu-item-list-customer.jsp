<%@ include file="common/header.jspf"%>
<%@ include file="common/nav_cust.jspf"%>
<div class="container">
	<h1>Menu Items</h1>
	<c:if test="${addCartStatus }"><h4 id="cart-sucess">Item added to Cart Successfully</h4></c:if>
	<table id="cust-menu">
		<tr>
			<th>Name</th>
			<th>Free Delivery</th>
			<th>Price</th>
			<th>Category</th>
			<th>Action</th>
		</tr>

		<c:forEach items="${menuItemListCustomer}" var="menuItem">
			<tr>
				<td>${menuItem.name}</td>
				<td><c:choose>
						<c:when test="${menuItem.freeDelivery }">Yes</c:when>
						<c:otherwise>No</c:otherwise>
					</c:choose>
				</td>
				<td>Rs.${menuItem.price}</td>
				<td><c:choose>
						<c:when test="${menuItem.active }">Yes</c:when>
						<c:otherwise>No</c:otherwise>
					</c:choose>
				</td>
				<td>${menuItem.category }</td>
					
				<td><a href="/add-to-cart?id=${menuItem.id}">Add to Cart</a></td>				
			</tr>


		</c:forEach>

	</table>

</div>
<%@ include file="common/footer.jspf"%>