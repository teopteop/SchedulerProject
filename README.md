### api 명세서

---
| 기능 | Method | URL       | request                                                                                            | response            | 상태코드           |
|----|--------|-----------|----------------------------------------------------------------------------------------------------|---------------------|----------------|
| 생성 | POST   | /schedule | **requestBody**<br>{<br> "task": "task",<br>  "author": "author",<br>  "password": "password"<br>} | {<br>  "id": 1<br>} | `201` : create |


### ERD

---
![SchedulerERD.png](src/img/SchedulerERD.png)