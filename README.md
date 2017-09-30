# Alfresco Photo Analyzer Module - Global Hack-a-thon 2017

Alfresco customization to extracts information of upload photos. 

The list of possible information to extract from a photo is:
  + Number of persons
  + Ages
  + Gender (![alt text](https://github.com/davidantonlou/alfrescoPhotoAnalyzer/tree/master/alfrescoPhotoAnalyzer-share-jar/src/main/resources/META-INF/images/MALE.png) ![alt text](https://github.com/davidantonlou/alfrescoPhotoAnalyzer/tree/master/alfrescoPhotoAnalyzer-share-jar/src/main/resources/META-INF/images/FEMALE.png))
  + Hair (![alt text](https://github.com/davidantonlou/alfrescoPhotoAnalyzer/tree/master/alfrescoPhotoAnalyzer-share-jar/src/main/resources/META-INF/images/BLACK.png) ![alt text](https://github.com/davidantonlou/alfrescoPhotoAnalyzer/tree/master/alfrescoPhotoAnalyzer-share-jar/src/main/resources/META-INF/images/BROWN.png) ![alt text](https://github.com/davidantonlou/alfrescoPhotoAnalyzer/tree/master/alfrescoPhotoAnalyzer-share-jar/src/main/resources/META-INF/images/GRAY.png) ![alt text](https://github.com/davidantonlou/alfrescoPhotoAnalyzer/tree/master/alfrescoPhotoAnalyzer-share-jar/src/main/resources/META-INF/images/RED.png) ![alt text](https://github.com/davidantonlou/alfrescoPhotoAnalyzer/tree/master/alfrescoPhotoAnalyzer-share-jar/src/main/resources/META-INF/images/BLOND.png) ![alt text](https://github.com/davidantonlou/alfrescoPhotoAnalyzer/tree/master/alfrescoPhotoAnalyzer-share-jar/src/main/resources/META-INF/images/BALD.png))
  + Facial Hair (![alt text](https://github.com/davidantonlou/alfrescoPhotoAnalyzer/tree/master/alfrescoPhotoAnalyzer-share-jar/src/main/resources/META-INF/images/BEARD.png) ![alt text](https://github.com/davidantonlou/alfrescoPhotoAnalyzer/tree/master/alfrescoPhotoAnalyzer-share-jar/src/main/resources/META-INF/images/MOUSTACHE.png) ![alt text](https://github.com/davidantonlou/alfrescoPhotoAnalyzer/tree/master/alfrescoPhotoAnalyzer-share-jar/src/main/resources/META-INF/images/SIDEBURNS.png))
  + Emotions (![alt text](https://github.com/davidantonlou/alfrescoPhotoAnalyzer/tree/master/alfrescoPhotoAnalyzer-share-jar/src/main/resources/META-INF/images/ANGER.png) ![alt text](https://github.com/davidantonlou/alfrescoPhotoAnalyzer/tree/master/alfrescoPhotoAnalyzer-share-jar/src/main/resources/META-INF/images/CONTEMPT.png) ![alt text](https://github.com/davidantonlou/alfrescoPhotoAnalyzer/tree/master/alfrescoPhotoAnalyzer-share-jar/src/main/resources/META-INF/images/DISGUST.png) ![alt text](https://github.com/davidantonlou/alfrescoPhotoAnalyzer/tree/master/alfrescoPhotoAnalyzer-share-jar/src/main/resources/META-INF/images/FEAR.png ![alt text](https://github.com/davidantonlou/alfrescoPhotoAnalyzer/tree/master/alfrescoPhotoAnalyzer-share-jar/src/main/resources/META-INF/images/HAPPINESS.png) ![alt text](https://github.com/davidantonlou/alfrescoPhotoAnalyzer/tree/master/alfrescoPhotoAnalyzer-share-jar/src/main/resources/META-INF/images/SADNESS.png) ![alt text](https://github.com/davidantonlou/alfrescoPhotoAnalyzer/tree/master/alfrescoPhotoAnalyzer-share-jar/src/main/resources/META-INF/images/NEUTRAL.png) ![alt text](https://github.com/davidantonlou/alfrescoPhotoAnalyzer/tree/master/alfrescoPhotoAnalyzer-share-jar/src/main/resources/META-INF/images/SURPRISE.png)
  + Accessories (![alt text](https://github.com/davidantonlou/alfrescoPhotoAnalyzer/tree/master/alfrescoPhotoAnalyzer-share-jar/src/main/resources/META-INF/images/READGLASSES.png) ![alt text](https://github.com/davidantonlou/alfrescoPhotoAnalyzer/tree/master/alfrescoPhotoAnalyzer-share-jar/src/main/resources/META-INF/images/SUNGLASSES.png) ![alt text](https://github.com/davidantonlou/alfrescoPhotoAnalyzer/tree/master/alfrescoPhotoAnalyzer-share-jar/src/main/resources/META-INF/images/HEADWEAR.png)))
 


# Build and Run Alfresco

   This project uses:
      * Alfresco 5.2 with SDK 3.0.0
      * Microsoft Azure API
  
   Run embedded Tomcat + H2 DB with `mvn clean install -DskipTests=true alfresco:run` or `./run.sh` 

 
# Configure Microsoft Azure API

   To configure Azure API connection, change the following properties in the alfresco-global.properties file.

  `photo_analyzer.azure.api.url=https://westeurope.api.cognitive.microsoft.com/face/v1.0/detect`
  `photo_analyzer.azure.api.attribute_list=age,gender,emotion,hair,facialHair,accessories,glasses`
  `photo_analyzer.azure.api.subscription_key=4ef1782a6a0f473a86b3f139dc7457f3`
 