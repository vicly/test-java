


Saying: 						representation class
HelloWorldResource: 			resource class
HelloApplication: 				application class
HelloApplicationConfiguration:	configuration class
TemplateHealthCheck:			health check class



metrics:
  reporters:
    - type: ganglia
      host: localhost
      port: 8649
      mode: unicast
      ttl: 1
      uuid: de305d54-75b4-431b-adb2-eb6b9e546014
      spoof: localhost:8649
      tmax: 60
      dmax: 0