PhoneGap-SMSPlugin
==================

What is it?
-----------
This is a very simple plugin for sending SMS messages from PhoneGap for Android.

How do I install it?
--------------------

*  Copy SMS.java into your src/ folder.
*  Copy SMS.js into your assets/www/ folder.
*  Add the contents of plugins.xml to your assets/xml/plugins.xml file.
*  Reference SMS.js via script tag from any page where you intend to use SMS.
*  Roll with it.

How do I use it?
----------------

Make sure that you've properly referenced the SMS.js file in your HTML like so:

>  <script src="cordova-1.6.0.js"></script>
>  <script src="SMS.js"></script>

Sending an SMS is as simple as calling the SMSPlugin.send function:

>  window.plugins.SMSPlugin.send("3048675309","Jenny! Jenny!",successfulSMS, unsuccessfulSMS);

SMSPlugin.send takes four parameters: a string phone number, the contents of the text, a JS callback on a successfulSMS, and a JS callback on an unsuccessful SMS (the last two parameters are more for consistency with how PhoneGap does plugins).

What else will be done to improve this?
---------------------------------------

*  Support multi-part texts.
*  Support opening SMS application to compose message.
*  Properly handle successful and unsuccessful callbacks (currently always successful and reports success/failure with Toast messages.

What license is this under?
---------------------------

This work is released under the Apache License version 2.0 which is available at http://www.apache.org/licenses/LICENSE-2.0.html.
