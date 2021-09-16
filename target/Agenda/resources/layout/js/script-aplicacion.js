const cerrarModal = (modal, args) => {
	var $modal = modal.jq;
	if (args.validationFailed) {
		$modal.effect(
			"shake",
			{
				times: 3,
			},
			100
		);
	} else {
		modal.hide();
	}
};

const borrarFiltro = (dataTable, args) => {
	if (!args.validationFailed) {
		dataTable.clearFilters();
	}
};
