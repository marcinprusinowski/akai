# CREDIT USERS API 
#####REST JSON API returns user basic and detailed information 
 
<br>
 
`GET credit-users/{pid}`

Success
200

content-type: application/json
```
{
    id: string
    name: string
    pid: string
    surname: string
}
```
Error 404 Not found

```
Empty body
``` 
<br>
<br>
<br>
<br>

`GET credit-users/{pid}/details`

Success
200

content-type: application/json
```
{
    address: string
    age: number
    country: string
    id: string
    pid: string
}
```
Error 404 Not found

```
Empty body
``` 
