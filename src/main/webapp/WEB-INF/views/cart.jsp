<%@ include file="common/header.jspf"%>
<%@ include file="common/nav_cust.jspf"%>
<div class="container">
	<h1>Cart</h1>
	<c:if test="${removeCartItemStatus }"><h4 id="cart-sucess">Item removed from Cart Successfully</h4></c:if>
	<table id="cust-menu">
		<tr>
			<th>Name</th>
			<th>Free Delivery</th>
			<th>Price</th>
			<th>Action</th>
		</tr>

		<c:forEach items="${cart.menuItemList}" var="menuItem">
			<tr>
				<td>${menuItem.name}</td>
				<td><c:choose>
						<c:when test="${menuItem.freeDelivery }">Yes</c:when>
						<c:otherwise>No</c:otherwise>
					</c:choose>
				</td>
				<td>Rs.${menuItem.price}</td>
		
					
				<td><a href="/remove-cart?id=${menuItem.id}&userId=1">Delete</a></td>				
			</tr>


		</c:forEach>
		<tr>
		<th></th>
		<th>Total</th>
		<th>Rs.${cart.total }</th>
		</tr>

	</table>

</div>
<%@ include file="common/footer.jspf"%>