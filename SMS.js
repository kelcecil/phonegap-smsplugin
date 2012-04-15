var SMS = function() {};

SMS.prototype.send = function(phoneNumber,message,successCallback,failureCallback) {
    return PhoneGap.exec( successCallback, failureCallback, 'SMSPlugin', 'send', [phoneNumber, message]);
};

PhoneGap.addConstructor(function() {
    PhoneGap.addPlugin("SMSPlugin", new SMS());
});