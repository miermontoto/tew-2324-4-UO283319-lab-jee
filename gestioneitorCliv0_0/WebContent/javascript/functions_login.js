function Model() {
	this.setToken = function(token) {
		// TODO: almacenar el token
	}

	this.login = function(user) {
		return LoginServiceRs.login({
			$entity: user,
			$contentType: "application/json"
		});
	}
};

function View() {
	this.loadUserFromForm = function() {
		return {
			login: $("#username").val(),
			password: $("#passwd").val()
		};
	}

	this.loadUserInForm = function(user) {
		$("#username").val(user.login);
		$("#passwd").val(user.password);
	}
}

function Controller(varmodel, varview) {
	var that = this;
	this.model = varmodel;
	this.view = varview;

	this.init = function() {
		$("#login").bind("submit", function(event) {
			user = that.view.loadUserFromForm
			var token = that.model.login(user);
			console.log(token);

			if (token == "") {
				// TODO: mostrar mensaje de login incorrecto
			} else {
				that.model.setToken(token);
				$("#formulario").attr("data", "crudAlumno.html");
			}
		});
	}
}

$(function() {
	var model = new Model();
	var view = new View();
	var control = new Controller(model, view);

	control.init();
})
