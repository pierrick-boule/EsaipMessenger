EsaipMessenger
==============

Projet Android TP

!!! Bug avec !!!  
EsaipMessenger\.apt_generated\org\esaip\messenger\rest\RestClient_.java

la ligne : restTemplate = new RestTemplate();
doit etre chang�e par : restTemplate = new RestTemplate(true);
avant la compilation.