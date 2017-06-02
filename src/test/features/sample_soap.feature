#language:zh-CN
功能: Webservice 样本

  背景: 
    * BASE http://www.webservicex.net
    * HEADER
      """
      Content-Type: text/xml; charset=utf-8
      Accept: application/soap+xml
      """

  场景: 查天气
    * POST /globalweather.asmx
      """
      <?xml version="1.0" encoding="utf-8"?>
      <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
      <soap:Body>
      <GetCitiesByCountry xmlns="http://www.webserviceX.NET">
      <CountryName>China</CountryName>
      </GetCitiesByCountry>
      </soap:Body>
      </soap:Envelope>
      """
    * STATUS 200
    * CONTAINS China
