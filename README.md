# NotaBene

Android app for speech recognize some notes and send it to email with using as minimum touches as possible.

Some good ideas apear in time of driving a car. By using this application driver can send those ideas to own email for reconsider it later.
Firstly application can recognise a speech, also manual input option is possible.
Than application send recognised text to email spesified in application settings with JavaMailAPI(internet is required). In case of lack of internet connection the application will create an intent and email will be sent by default email client as soon as internet connection established.

Plans for improvement.
This version require gmail acount for sender. 2-factor authentication off and Less secure application option should be used in Google security settings which is not optimal. So more secure authorisation method is under development for next vertion of the application.
