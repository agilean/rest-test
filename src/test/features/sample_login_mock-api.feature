#language:zh-CN
功能: 登录mock-api.com的样本

  背景:
    * BASE http://localhost:8080
    * HEADER
      """
      bar = foo
      fuck = suck
      """
    * COOKIE
      """
      BB=FF
      """
    * POST FORM /j_spring_security_check
      """
      username = demo
      password = demo
      """
    * STATUS 200
    * JSONPATH result ok

  场景: 测试
    * GET /users
    * STATUS 200
    * JSONPATH [0].user alex
