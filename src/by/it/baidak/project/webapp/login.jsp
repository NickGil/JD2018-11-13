<html>
<%@ include file="include/head.htm" %>
<body>
<%@ include file="include/menu.htm" %>

<form class="form-horizontal" action="do?command=Login" method="POST">
<fieldset>

<!-- Form Name -->
<legend>Log in</legend>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="login">Login</label>
  <div class="col-md-4">
  <input id="login" name="login" value="TestUser" type="text" placeholder="min 6 symbols" class="form-control input-md" required="">

  </div>
</div>

<!-- Password input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="password">Password</label>
  <div class="col-md-4">
    <input id="password" name="password" value="TestPassword" type="password" placeholder="" class="form-control input-md" required="">

  </div>
</div>

<!-- Button -->
<div class="form-group">
  <label class="col-md-4 control-label" for="signup"></label>
  <div class="col-md-4">
    <button id="login" name="lohin" class="btn btn-success">Login</button>
  </div>
</div>
</fieldset>
</form>

</body>
</html>

