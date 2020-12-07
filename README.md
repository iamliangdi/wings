
1. **完整的分支**：
    - **master**：该分支内代码经测试后达到预期效果，格式较为规范；
    - **hotfix**：该分支内针对线上版本的bug进行修复；
    - **release**：该分支作为版本的起点；
    - **develop**：该分支可获取最快速度更新，功能基本实现，格式基本规范；
    - **feature**：该分支为新功能的开发分支；

2. **完整的分支合并规范**：
     - **master**：仅接受合并请求，不接受任何提交；
     - **hotfix**：bug修复后合并回**master**和**develop**；
     - **release**：合并回**master**和**develop**；
     - **develop**：仅接受合并请求，不接受任何提交；
     - **feature**：仅合并至**develop**；
     - 说明：  
             1、--no-ff：不使用fast-forward方式合并，保留分支的commit历史；  
             2、--squash：使用squash方式合并，把多次分支commit历史压缩为一次；  
             3、每个commit都应有其独自存在的意义，否则建议--squash忽略掉那些提交的乱七八糟的分支；
3. **commit提交规范**：
    - 遵循**type(scope):subject**格式，本项目忽略scope，type支持以下7种；
    - feat|更新：新功能；
    - fix|修复: bug修复；
    - docs|文档：修改文档；
    - perf|优化: 性能优化；
    - test|测试: 增加测试；
    - revert|移除： 撤销；
    - refactor|重构: 重构；