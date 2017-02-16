$(function () {
    var $searchCustomerButton = $('#searchCustomerButton'),
    $customerIdInput = $('#customerId'),
    $customerIdMessage = $('#customerIdMessage'),
    $customerIdGroup = $('#customerIdGroup'),
    $companyNameInput = $('#companyName'),
    $companyAddressInput = $('#companyAddress'),
    $companyPostalCodeInput = $('#companyPostalCode'),
    $companyCityInput = $('#companyCity')
//    $companyContactNameInput = $('#companyContactName'),
//    $companyEmailInput = $('#companyEmail')
    ;

    $searchCustomerButton.click(function (e) {
        var customerId = $customerIdInput.val();

        var jqxhr = $.ajax({
            url: '/StihoComplaints/autofill/' + customerId,
            dataType: 'json'
        }).done(function (data, textStatus, jqXHR) {
            $customerIdGroup.removeClass('has-warning').addClass('has-success');
            $companyNameInput.val(data.companyName);
            $companyAddressInput.val(data.companyAddress);
            $companyPostalCodeInput.val(data.companyPostalCode);
            $companyCityInput.val(data.companyCity);
//            $companyContactNameInput.val(data.contactName);
//            $companyEmailInput.val(data.email);
            $customerIdMessage.text('Klantnummer bekend.');
        })
        .fail(function () {
            $customerIdGroup.removeClass('has-success').addClass('has-warning');
            $companyNameInput.val("");
            $companyAddressInput.val("");
            $companyPostalCodeInput.val("");
            $companyCityInput.val("");
//            $companyContactNameInput.val("");
//            $companyEmailInput.val("");
            $customerIdMessage.text('Klantnummer niet gevonden!');
        });
    });
});