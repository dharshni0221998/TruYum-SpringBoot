<%@ include file="common/header.jspf"%>
<%@ include file="common/nav_admin.jspf"%>
<div class="container">
	<h1>Menu Items</h1>
	<table id="admin-menu">
		<tr>
			<th>Name</th>
			<th>Price</th>
			<th>Active</th>
			<th>Date of Lunch</th>
			<th>Category</th>
			<th>Free Delivery</th>
			<th>Action</th>
		</tr>

		<c:forEach items="${menuItemListAdmin}" var="menuItem">
			<tr>
				<td>${menuItem.name}</td>
				<td>Rs.${menuItem.price}</td>
				<td><c:choose>
						<c:when test="${menuItem.active }">Yes</c:when>
						<c:otherwise>No</c:otherwise>
					</c:choose>
				</td>
				<td><fmt:formatDate value="${menuItem.dateOfLaunch}" pattern="dd/MM/yyyy"/></td>
				<td>${menuItem.category }</td>
				<td><c:choose>
						<c:when test="${menuItem.freeDelivery }">Yes</c:when>
						<c:otherwise>No</c:otherwise>
					</c:choose>
				</td>	
				<td><a href="show-edit-menu-item?id=${menuItem.id}">Edit</a></td>				
			</tr>


		</c:forEach>

	</table>

</div>
<%@ include file="common/footer.jspf"%>