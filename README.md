# Alfresco Photo Analyzer Module - Global Hack-a-thon 2017

Alfresco customization to extracts information of upload photos. 

The list of possible information to extract from a photo is:
  + Number of persons
  + Ages
  + Gender  <img src="images/MALE.png?raw=true" width="12" height="12"> <img src="images/FEMALE.png?raw=true" width="12" height="12">
  + Hair  <img src="images/BLACK.png?raw=true" width="12" height="12"> <img src="images/BROWN.png?raw=true" width="12" height="12">  <img src="images/GRAY.png?raw=true" width="12" height="12">  <img src="images/RED.png?raw=true" width="12" height="12">  <img src="images/BLOND.png?raw=true" width="12" height="12"> <img src="images/BALD.png?raw=true" width="12" height="12">
  + Facial Hair  <img src="images/BEARD.png?raw=true" width="12" height="12"> <img src="images/MOUSTACHE.png?raw=true" width="12" height="12"> <img src="images/SIDEBURNS.png?raw=true" width="12" height="12">
  + Emotions  <img src="images/ANGER.png?raw=true" width="12" height="12"> <img src="images/CONTEMPT.png?raw=true" width="12" height="12"> <img src="images/DISGUST.png?raw=true" width="12" height="12"> <img src="images/FEAR.png?raw=true" width="12" height="12"> <img src="images/HAPPINESS.png?raw=true" width="12" height="12"> <img src="images/NEUTRAL.png?raw=true" width="12" height="12"> <img src="images/SADNESS.png?raw=true" width="12" height="12"> <img src="images/SURPRISE.png?raw=true" width="12" height="12">
  + Accessories  <img src="images/READINGGLASSES.png?raw=true" width="12" height="12"> <img src="images/SUNGLASSES.png?raw=true" width="12" height="12"> <img src="images/HEADWEAR.png?raw=true" width="12" height="12">
 

# Build and Run Alfresco

   This project uses:
      + Alfresco 5.2 with SDK 3.0.0
      + Microsoft Azure API
  
   Run embedded Tomcat + H2 DB with `mvn clean install -DskipTests=true alfresco:run` or `./run.sh` 

 
# Configure Microsoft Azure API

   To configure Azure API connection, change the following properties in the alfresco-global.properties file.

  `photo_analyzer.azure.api.url=https://westeurope.api.cognitive.microsoft.com/face/v1.0/detect`
  `photo_analyzer.azure.api.attribute_list=age,gender,emotion,hair,facialHair,accessories,glasses`
  `photo_analyzer.azure.api.subscription_key=4ef1782a6a0f473a86b3f139dc7457f3`
 