# Alfresco Photo Analyzer Module - Global Hack-a-thon 2017

Alfresco customization to extracts information of upload photos. 

The list of possible information to extract from a photo is:
  + Number of persons
  + Ages
  + Gender  <img src="https://github.com/davidantonlou/alfrescoPhotoAnalyzer/blob/master/alfrescoPhotoAnalyzer-share-jar/src/main/resources/META-INF/images/MALE.png?raw=true" width="12" height="12"> <img src="https://github.com/davidantonlou/alfrescoPhotoAnalyzer/blob/master/alfrescoPhotoAnalyzer-share-jar/src/main/resources/META-INF/images/FEMALE.png?raw=true" width="12" height="12">
  + Hair  <img src="https://github.com/davidantonlou/alfrescoPhotoAnalyzer/blob/master/alfrescoPhotoAnalyzer-share-jar/src/main/resources/META-INF/images/BLACK.png?raw=true" width="12" height="12"> <img src="https://github.com/davidantonlou/alfrescoPhotoAnalyzer/blob/master/alfrescoPhotoAnalyzer-share-jar/src/main/resources/META-INF/images/BROWN.png?raw=true" width="12" height="12">  <img src="https://github.com/davidantonlou/alfrescoPhotoAnalyzer/blob/master/alfrescoPhotoAnalyzer-share-jar/src/main/resources/META-INF/images/GRAY.png?raw=true" width="12" height="12">  <img src="https://github.com/davidantonlou/alfrescoPhotoAnalyzer/blob/master/alfrescoPhotoAnalyzer-share-jar/src/main/resources/META-INF/images/RED.png?raw=true" width="12" height="12">  <img src="https://github.com/davidantonlou/alfrescoPhotoAnalyzer/blob/master/alfrescoPhotoAnalyzer-share-jar/src/main/resources/META-INF/images/BLOND.png?raw=true" width="12" height="12"> <img src="https://github.com/davidantonlou/alfrescoPhotoAnalyzer/blob/master/alfrescoPhotoAnalyzer-share-jar/src/main/resources/META-INF/images/BALD.png?raw=true" width="12" height="12">
  + Facial Hair  <img src="https://github.com/davidantonlou/alfrescoPhotoAnalyzer/blob/master/alfrescoPhotoAnalyzer-share-jar/src/main/resources/META-INF/images/BEARD.png?raw=true" width="12" height="12"> <img src="https://github.com/davidantonlou/alfrescoPhotoAnalyzer/blob/master/alfrescoPhotoAnalyzer-share-jar/src/main/resources/META-INF/images/MOUSTACHE.png?raw=true" width="12" height="12"> <img src="https://github.com/davidantonlou/alfrescoPhotoAnalyzer/blob/master/alfrescoPhotoAnalyzer-share-jar/src/main/resources/META-INF/images/SIDEBURNS.png?raw=true" width="12" height="12">
  + Emotions  <img src="https://github.com/davidantonlou/alfrescoPhotoAnalyzer/blob/master/alfrescoPhotoAnalyzer-share-jar/src/main/resources/META-INF/images/ANGER.png?raw=true" width="12" height="12"> <img src="https://github.com/davidantonlou/alfrescoPhotoAnalyzer/blob/master/alfrescoPhotoAnalyzer-share-jar/src/main/resources/META-INF/images/CONTEMPT.png?raw=true" width="12" height="12"> <img src="https://github.com/davidantonlou/alfrescoPhotoAnalyzer/blob/master/alfrescoPhotoAnalyzer-share-jar/src/main/resources/META-INF/images/DISGUST.png?raw=true" width="12" height="12"> <img src="https://github.com/davidantonlou/alfrescoPhotoAnalyzer/blob/master/alfrescoPhotoAnalyzer-share-jar/src/main/resources/META-INF/images/FEAR.png?raw=true" width="12" height="12"> <img src="https://github.com/davidantonlou/alfrescoPhotoAnalyzer/blob/master/alfrescoPhotoAnalyzer-share-jar/src/main/resources/META-INF/images/HAPPINESS.png?raw=true" width="12" height="12"> <img src="https://github.com/davidantonlou/alfrescoPhotoAnalyzer/blob/master/alfrescoPhotoAnalyzer-share-jar/src/main/resources/META-INF/images/NEUTRAL.png?raw=true" width="12" height="12"> <img src="https://github.com/davidantonlou/alfrescoPhotoAnalyzer/blob/master/alfrescoPhotoAnalyzer-share-jar/src/main/resources/META-INF/images/SADNESS.png?raw=true" width="12" height="12"> <img src="https://github.com/davidantonlou/alfrescoPhotoAnalyzer/blob/master/alfrescoPhotoAnalyzer-share-jar/src/main/resources/META-INF/images/SURPRISE.png?raw=true" width="12" height="12">
  + Accessories  <img src="https://github.com/davidantonlou/alfrescoPhotoAnalyzer/blob/master/alfrescoPhotoAnalyzer-share-jar/src/main/resources/META-INF/images/READINGGLASSES.png?raw=true" width="12" height="12"> <img src="https://github.com/davidantonlou/alfrescoPhotoAnalyzer/blob/master/alfrescoPhotoAnalyzer-share-jar/src/main/resources/META-INF/images/SUNGLASSES.png?raw=true" width="12" height="12"> <img src="https://github.com/davidantonlou/alfrescoPhotoAnalyzer/blob/master/alfrescoPhotoAnalyzer-share-jar/src/main/resources/META-INF/images/HEADWEAR.png?raw=true" width="12" height="12">
 

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
 