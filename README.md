# Restaurant Management System - RMS

## Technologies Used
- IntelliJ IDEA
- Postman
- Java SpringBoot (v3.5.6)
- Java (v17)

## Access the application

- Run at with path : http://localhost:8001/localhost:8001/path name
- **API**
    #### Customer Module
    - http://localhost:8001/api/customer/getAll
    - http://localhost:8001/api/customer/create
    - http://localhost:8001/api/customer/update
    - http://localhost:8001/api/customer/delete/{deleteId}

    #### Order module ( create order, orderDetail and billing)
    - http://localhost:8001/api/order/create
    - http://localhost:8001/api/order/update/{orderId}
    - http://localhost:8001/api/order/delete/{orderId}
    #### Billing module

    - http://localhost:8001/api/billing/{billingId}/status
    - http://localhost:8001/api/customer/delete/{billingId}
    #### Payment module
    - http://localhost:8001/api/customer/create
    - http://localhost:8001/api/customer/update/{paymentId}
    - http://localhost:8001/api/customer/delete/{paymentId}
    #### Product module
    - http://localhost:8001/api/category/getAll
    - http://localhost:8001/api/category/create
    - http://localhost:8001/api/category/update/{categoryId}
    - http://localhost:8001/api/category/delete/{categoryId}
    #### Product module
    - http://localhost:8001/api/product/category/{categoryId}
    - http://localhost:8001/api/product/{productId}
    - http://localhost:8001/api/product/update/{productId}
    - http://localhost:8001/api/product/delete/{productId}

    #### Table module
    - http://localhost:8001/api/table_mangement/getAll
    - http://localhost:8001/api/table_mangement/create
    - http://localhost:8001/api/table_mangement/update/{tableId}
    - http://localhost:8001/api/table_mangement/delete/{tableId}


## Installation
- Move to project branch 
```bash
    git checkout master
```
- Run project 
```bash
    B1: open RmsApplication.java
    B2: key: Shift + F10
```
