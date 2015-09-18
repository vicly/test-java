

# WSDL basic

## WSDL file main structure
```xml
<definitions>
   <types>
      definition of types........
   </types>
   <message>
      the data being exchanged between the web service providers and the consumers
   </message>
   <portType>
      <operation>
         form a complete one-way or request-response operation.
      </operation>
   </portType>
   <binding>
      definition of a binding....
      provides specific details on how a portType operation will actually be transmitted over the wire
      The bindings can be made available via multiple transports including HTTP GET, HTTP POST, or SOAP
      For SOAP protocol, the binding is <soap:binding>, and the transport is SOAP messages on top of HTTP protocol.
      You can specify multiple bindings for a single portType.
   </binding>
   <service>
      definition of a service....
   </service>
</definitions>
```

## Sample WSDL
HelloService.wsdl

function: sayHello

```xml
<!--
    name: the document name
    targetNamespace: namespace of itself
    xmlns: default namespace
    xmlns:soap/xmlns:tns/xmlns:xsd: numerous namespaces

    NameSpace
    The namespace specification does not require the document to be present at the given location. The important point is that you specify a value that is unique, different from all other namespaces that are defined
-->

<definitions name="HelloService"
   targetNamespace="http://www.examples.com/wsdl/HelloService.wsdl"
   xmlns="http://schemas.xmlsoap.org/wsdl/"
   xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/"
   xmlns:tns="http://www.examples.com/wsdl/HelloService.wsdl"
   xmlns:xsd="http://www.w3.org/2001/XMLSchema">
   <message name="SayHelloRequest">
      <!-- 0 or more <part> -->
      <part name="firstName" type="xsd:string"/>
   </message>
   <message name="SayHelloResponse">
      <part name="greeting" type="xsd:string"/>
   </message>
   <!-- sayHello operation -->
   <portType name="Hello_PortType">
      <operation name="sayHello">
         <input message="tns:SayHelloRequest"/>
         <output message="tns:SayHelloResponse"/>
      </operation>
   </portType>
   <binding name="Hello_Binding" type="tns:Hello_PortType">
      <soap:binding style="rpc"
         transport="http://schemas.xmlsoap.org/soap/http"/>
      <operation name="sayHello">
         <soap:operation soapAction="sayHello"/>
         <input>
            <soap:body
               encodingStyle="http://schemas.xmlsoap.org/soap/encoding/"
               namespace="urn:examples:helloservice"
               use="encoded"/>
         </input>
         <output>
            <soap:body
               encodingStyle="http://schemas.xmlsoap.org/soap/encoding/"
               namespace="urn:examples:helloservice"
               use="encoded"/>
         </output>
      </operation>
   </binding>
   <!-- serive available at http://www.examples.com/SayHello/ -->
   <service name="Hello_Service">
      <documentation>WSDL File for HelloService</documentation>
      <port binding="tns:Hello_Binding" name="Hello_Port">
         <soap:address
            location="http://www.examples.com/SayHello/" />
      </port>
   </service>
</definitions>
```

## Sample <types>
```xml
<types>
   <schema targetNamespace="http://example.com/stockquote.xsd"
      xmlns="http://www.w3.org/2000/10/XMLSchema">
      <element name="TradePriceRequest">
         <complexType>
            <all>
               <element name="tickerSymbol" type="string"/>
            </all>
         </complexType>
      </element>
      <element name="TradePrice">
         <complexType>
            <all>
               <element name="price" type="float"/>
            </all>
         </complexType>
      </element>
   </schema>
</types>
```

## Operation Patterns
### One Way
Just receive message
```xml
<wsdl:portType .... > *
  <wsdl:operation name="nmtoken">
    <wsdl:input name="nmtoken"? message="qname"/>
  </wsdl:operation>
</wsdl:portType >
```

### Request Response
Recieve message then send a response
```xml
<wsdl:portType .... > *
  <wsdl:operation name="nmtoken">
    <wsdl:input name="nmtoken"? message="qname"/>
    <wsdl:output name="nmtoken"? message="qname"/>
    <wsdl:fault name="nmtoken" message="qname"/> *
  </wsdl:operation>
</wsdl:portType >
```

### Solicit Response
Send a message then receives a response
```xml
<wsdl:portType .... > *
  <wsdl:operation name="nmtoken">
    <wsdl:output name="nmtoken"? message="qname"/>
    <wsdl:input name="nmtoken"? message="qname"/>
    <wsdl:fault name="nmtoken" message="qname"/> *
  </wsdl:operation>
</wsdl:portType >
```

### Notification
Just send a message
```xml
<wsdl:portType .... > *
  <wsdl:operation name="nmtoken">
    <wsdl:output name="nmtoken"? message="qname"/>
  </wsdl:operation>
</wsdl:portType >
```






# SOAP Basic
## SOAP Message Structure
```xml
<?xml version="1.0"?>
<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://www.w3.org/2001/12/soap-envelope" SOAP-ENV:encodingStyle="http://www.w3.org/2001/12/soap-encoding">
   <SOAP-ENV:Header>
      ...
      ...
   </SOAP-ENV:Header>
   <SOAP-ENV:Body>
      ...
      ...
      <SOAP-ENV:Fault>
         ...
         ...
      </SOAP-ENV:Fault>
      ...
   </SOAP-ENV:Body>
</SOAP_ENV:Envelope>
```
