<template>
  <div class="homeBox">
  <el-container style="height: 100%; border: 0px solid #eee">
    <el-aside width="200px" style="background-color: rgb(238, 241, 246);min-height: 100%;margin-right: 10px" >
      <el-menu :default-openeds="['1', '3']"  style="margin-right: 10px;overflow-x: hidden;min-height: 100%"
               background-color="rgb(48, 65, 86)"
               text-color="#fff"
               active-text-color="#ffd04b">
        <el-submenu index="1">
          <template slot="title" style="color: #42b983"><i class="el-icon-message"></i>林锴城</template>
          <el-menu-item-group>
            <!--            <template slot="title">分组一</template>-->
            <el-menu-item index="1-1" @click="$router.push('/home')">主页</el-menu-item>
            <el-menu-item index="1-2" @click="this.$router.go(0)">数据统计</el-menu-item>
          </el-menu-item-group>
        </el-submenu>
      </el-menu>
    </el-aside>

    <el-container>

      <el-main>
        <div>
          <el-row :gutter="10" style="margin-bottom: 30px">
            <el-col :span="6">
              <el-card style="color: #67C23A;size: auto" >
                <div><i class="el-icon-user-solid" /> {{ statisticValue }} </div>
                <div style="padding: 10px 0; text-align: center; font-weight: bold">
                  {{total}}
                </div>
              </el-card>
            </el-col>
          </el-row>
          <el-form style="color: cornflowerblue">请选择统计条目
            <el-select v-model="statisticValue" placeholder="请选择统计类型" @change="selectHandler(statisticValue)" style="margin: 10px">
              <el-option
                  v-for="item in statisticOptions"
                  :key="item.statisticValue"
                  :label="item.label"
                  :value="item.statisticValue">
              </el-option>
            </el-select>
          </el-form>


        </div>
          <el-table :data="tableData" border stripe >
            <el-table-column prop="pno" label="编号" width="65">
            </el-table-column>
            <el-table-column prop="name" label="姓名" width="65">
            </el-table-column>
            <el-table-column prop="sex" label="性别" width="60">
            </el-table-column>
            <el-table-column prop="age" label="年龄" width="65">
            </el-table-column>
            <el-table-column prop="ptype" label="职务">
            </el-table-column>
            <el-table-column prop="title" label="职称" width="70">
            </el-table-column>
            <el-table-column prop="politic" label="政治面貌">
            </el-table-column>
            <el-table-column prop="education" label="教育程度">
            </el-table-column>
            <el-table-column prop="duration" label="任职时间" width="100">
            </el-table-column>
            <el-table-column prop="enterTime" label="来院时间" width="100">
            </el-table-column>
            <el-table-column prop="retired" label="退休">
            </el-table-column>
            <el-table-column prop="temp" label="临时工">
            </el-table-column>
          </el-table>

          <div>
            <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="PageNum"
                :page-sizes="[5, 10, 15, 20]"
                :page-size="PageSize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total">
            </el-pagination>
          </div>

      </el-main>

    </el-container>
  </el-container>
  </div>

</template>

<script>
import router from "@/router";
import * as echarts from 'echarts'
import request from "@/utils/request";
export default {
  name: "DataPanel",

  created() {
    this.load();
  },

  data() {
    return {
      PageNum:1,
      PageSize:5,
      tableData:[],
      total:0,
      statisticOptions: [{
        statisticValue: '总人数',
        label: ''
      }, {
        statisticValue: '在职人数',
        label: '在职人数'
      }, {
        statisticValue: '党员人数',
        label: '党员人数'
      }, {
        statisticValue: '女工人数',
        label: '女工人数'
      }, {
        statisticValue: '高学历高职称人数',
        label: '高学历高职称人数'
      }],
      statisticValue:"总人数",
    }
  },
  methods:{
    load(){
      request.post("http://114.132.156.246:9090/Person/getPageList",{
            pageNum:this.PageNum,
            pageSize:this.PageSize,
            rankAction:null,
            statisticAction:null,
          }
      ).then(res=> {
        this.tableData = res.data.personList
        this.total = res.data.sum
        console.log(res)
      })
    },
    selectHandler(statisticValue){
      if (statisticValue == "总人数"){
        statisticValue = null;
      }
      else if (statisticValue == "在职人数"){
        statisticValue = "onWorkingStatisticsAction";
      }
      else if (statisticValue == "党员人数"){
        statisticValue = "partyMemberStatisticsAction";
      }
      else if (statisticValue == "女工人数"){
        statisticValue = "womenStatisticsAction";
      }
      else if (statisticValue == "高学历高职称人数"){
        statisticValue = "eliteStatisticsAction";
      }
      console.log("loading")
      request.post("http://114.132.156.246:9090/Person/getPageList",{
            pageNum:this.PageNum,
            pageSize:this.PageSize,
            rankAction:null,
            statisticAction:statisticValue,
          }
      ).then(res=> {
        this.tableData = res.data.personList
        this.total = res.data.sum
        this.chartData = res.data
        console.log(res)
      })
    },
    handleSizeChange(PageSize){
      console.log(PageSize)
      this.PageSize=PageSize
      this.selectHandler(this.statisticValue);
    },
    handleCurrentChange(PageNum){
      console.log(PageNum)
      this.PageNum=PageNum
      this.selectHandler(this.statisticValue);
    },
  },

}
</script>

<style>
html,body,.homeBox,.el-container{
  /*设置内部填充为0，几个布局元素之间没有间距*/
  padding: 0px;
  /*外部间距也是如此设置*/
  margin: 0px;
  /*统一设置高度为100%*/
  height: 100%;
}
</style>