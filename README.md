# Getting Started

Project init using spring boot [initializr](https://start.spring.io/).

# Deferred decisions
 ## Hide full error stack
Hide error details when it occurs. 

# Decisions
 ## Move from JAKSON to GSON library
As it stand there is no simple clean way to ignore JSON fields dynamically using jakson, we'll have to either implement a viewer or explicitly list the fields to be excluded. Since most of the fields from the response I'm excluding I'm more interested in a dynamic exclussion strategy gson offers that. 
 ## In case of error on parsing return a Pokemon with null values rather than a null pokemon



# Production consideration
 ## Revisit the spring boot setup
A lot of spring boot autogenerated initial setup, might not be suitable for large production grade environment, but this can be slowly replaced.
 ## Organise request/responses in full objects
Depending on the future of this service. Might want to have the req/resp and objects dinamically generted from the swagger rather than manual parsing of the JSON response
 ## Multiple details
When requesting details for a Pokemon there are multiple versions of tests returned, currently just picking random most convinient. might want to find more appropriate set of business rules.