import request from '@/utils/request'

const base_api = '/admin/system/sysUser' //controller层@RequestMapping("/admin/system/sysUser")

// 用户列表
export const GetSysUserListByPage = (current,limit,queryDto) => {
  return request({
    //    @GetMapping("/findByPage/{pageNum}/{pageSize}")
    url: `${base_api}/findByPage/${current}/${limit}`, //路径
    method: 'get', //提交方式
    // 接口@RequestBody 前端 data : 名称，以json格式传输
    // 接口没有注解 ， 前端 params: 名称
    params: queryDto, //其他参数
  })
}

//用户添加
export const SaveSysUser = (sysUser) =>{
  return request({
    // @PostMapping("/saveSysUser")
    url: `${base_api}/saveSysUser`, //路径
    method: 'post', //提交方式
    data: sysUser, //其他参数
  })
}

//用户修改
export const UpdateSysUser = (sysUser) =>{
  //@PutMapping(value = "/updateSysUser")
  return request({
    url: `${base_api}/updateSysUser`, //路径
    method: 'put', //提交方式
    data: sysUser, //其他参数  对应export const UpdateSysUser = (sysUser) =>{
  })
}

//用户删除
export const DeleteSysUserById = (id) => {
  return request({
    //@DeleteMapping("/deleteById/{id}")
    url: `${base_api}/deleteById/${id}`, //路径
    method: 'delete', //提交方式
  })
}

// 给用户分配角色请求
export const DoAssignRoleToUser = (assginRoleVo) => {
  return request({
      url: "/admin/system/sysUser/doAssign",
      method: 'post',
      data: assginRoleVo
  })
}