# Demo application - Documents-Protocols

## Demo users - basic auth

| username   |      password      |  role |
|----------|:-------------:|------|
| demo_doc | pass | Document manager |
| demo_proc | pass | Protocol manager |
| demo_hop | pass | Head of protocol management, Protocol manager |

## API

###  Documents - ```/documents/document```
1. Create document - Document manager
    
```
URL: /
method: POST
URL variables: - 
Request body:
    type: Document
        name: String, mandatory
        type: String, mandatory
returns: 
    status: 200 OK/ 400 Bad Request
    body: 
        type: Message 
          message: Error description
```
2. Get document - Document manager

```
URL: /{documentId}
method: GET
URL variables: documentId - id of the document to find
Request body: - 
returns: 
    status: 200 OK/ 400 Bad Request
    body: 
        type: Document
             id: number
             name: String
             type: String
             created: date
             createdBy: user username
        type: Message 
          message: Error description
```

3. Update document - Document manager

```
URL: /{documentId}
method: PUT
URL variables: documentId - id of the document to update
Request body: 
        type: Document
             name: String
             type: String
returns: 
    status: 200 OK/ 400 Bad Request
    body: 
        type: Message 
          message: Error description
```
4. Delete document - Document manager

```
URL: /{documentId}
method: DELETE
URL variables: documentId - id of the document to delete
Request body: -
returns: 
    status: 200 OK/ 400 Bad Request
    body: 
        type: Message 
          message: Error description
```
### Protocols - ```/protocols/protocol```
1. Create protocol - Protocol manager
    
```
URL: /
method: POST
URL variables: - 
Request body:
    type: Protcol
        state: String, mandatory
        documents: list of type Document, mandatory if documentsIds is empty, new documents to create
        documentIds: list of existing documents ids, mandatory id documents field is empty
returns: 
    status: 200 OK/ 400 Bad Request
    body: 
        type: Message 
          message: Error description
```
2. Get protocol - Protocol manager

```
URL: /{protocolId}
method: GET
URL variables: protocolId - id of the protocol to find
Request body: - 
returns: 
    status: 200 OK/ 400 Bad Request
    body: 
        type: Protocol
             id: number
             state: String
             created: date
             createdBy: user username
             documents: list of assigned documents, type Document
             documentsIds: list of assigned documents ids
        type: Message 
          message: Error description
```

3. Update protocol - Head of protocol management

```
URL: /{protocolId}
method: PUT
URL variables: protocolId - id of the protocol to update
Request body: 
        type: Protcol
                state: String, mandatory
                documents: list of type Document, mandatory if documentsIds is empty, new documents to create
                documentIds: list of existing documents ids, mandatory id documents field is empty
returns: 
    status: 200 OK/ 400 Bad Request
    body: 
        type: Message 
          message: Error description
```
4. Update protocol state - Protocol manager

```
URL: /{protocolId}/state
method: PUT
URL variables: protocolId - id of the protocol to update
Request body: 
    type: string
returns: 
    status: 200 OK/ 400 Bad Request
    body: 
        type: Message 
          message: Error description
```

## Technologies

Java, Spring boot, Spring WEB, Spring JPA, Spring Security, Spring JPA Auditing, H2 DB.