const { ApolloGateway, IntrospectAndCompose } = require('@apollo/gateway');
const { ApolloServer, gql } = require('apollo-server');

const gateway = new ApolloGateway({
    supergraphSdl: new IntrospectAndCompose({
        subgraphs: [
            { name: 'movies', url: 'http://localhost:8081/graphql' },
            { name: 'productions', url: 'http://localhost:8082/graphql' },
            { name: 'talents', url: 'http://localhost:8083/graphql' },
        ],
   }),
});

const server = new ApolloServer({ gateway, subscriptions: false, tracing: true });
server.listen({ port: 8080 });