<%@ include file="common/header.jspf"%>
<%@ include file="common/nav_admin.jspf"%>
<div class="container">
	<h1>Edit Menu Item</h1>

	<div class="container">
		<table>
			<form:form action="edit-menu-item" name="menuItemForm"
				modelAttribute="menuItem" method="POST">
				<form:hidden path="id" value="${item.id}" />
				<tr>
					<td>Name</td>

				</tr>
				<tr>
					<td><form:input path="name" value="${item.name}"/> <form:errors style="color:red"
							path="name" /></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>Price (Rs.)</td>
					<td>Active</td>
					<td>Date Of Launch</td>
					<td>Category</td>
				</tr>
				<tr>
					<td><form:input path="price" value="${item.price}"/> <form:errors
							style="color:red" path="price" /></td>
					<td><form:radiobutton path="active" id="yes" value="true" />Yes
						<form:radiobutton path="active" id="no" value="false" />No <form:errors
							style="color:red" path="active" /></td>

					<td><form:input path="dateOfLaunch"  value="${item.dateOfLaunch }" /> <form:errors
							style="color:red" path="dateOfLaunch" /></td>
					<td><form:select path="category">
							<form:option value="Starters" label="Starters" />
							<form:option value="Main Course" label="Main Course" />
							<form:option value="Dessert" label="Dessert" />
							<form:option value="Drinks" label="Drinks" />
						</form:select></td>
				</tr>
				<tr>
					<td><form:checkbox path="freeDelivery" /> Free Delivery</td>
				</tr>
				<tr>
					<td><input type="submit" value="Save" /></td>
				</tr>


			</form:form>
		</table>
	</div>

</div>
<%@ include file="common/footer.jspf"%>