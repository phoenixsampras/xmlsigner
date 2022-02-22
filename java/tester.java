private static String readAllBytesJava(String filePath) 
{
    String content = "";

    try
    {
        content = new String ( Files.readAllBytes( Paths.get(filePath) ) );
    } 
    catch (IOException e) 
    {
        e.printStackTrace();
    }

    return content;
}
public static void firmarXML() throws URISyntaxException, ParserConfigurationException, XMLSecurityException, org.xml.sax.SAXException {
	
	String path = new File(Firmador.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();

    String xml = readAllBytesJava(path + "/original.xml");

    byte[] datos = xml.getBytes(StandardCharsets.UTF_8);

    try {


        PrivateKey privateKey = Firmador.getPrivateKey(path + "/privada.pem");

        X509Certificate cert =  Firmador.getX509Certificate(path + "/certificado.crt");

        byte[] xmlFirmado = Firmador.firmarDsig(datos, privateKey, cert);

        String respuesta = new String(xmlFirmado);

        System.out.println("facturaFirmada: "+respuesta);

    } catch (IOException | GeneralSecurityException ex) {

        //Logger.getLogger(Firmador.class.getName()).log(Level.k, null, ex);

    }

}