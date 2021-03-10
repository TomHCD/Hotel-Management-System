<template>
    <div>
        <a-layout>
<!--            <a-layout-sider width="20%" style="height: 100%; display: flex; justify-content: left; flex-wrap: wrap; background-color: transparent;">-->
                <a-form layout="horizontal"
                        style="text-align: center; margin: 20px 0px; width: 100%; display: flex; justify-content: space-between; flex-wrap: wrap; align-items: baseline">
                    <a-form-item class="searchDiv" label="酒店名" colon="false">
                        <a-input class="searchInput"
                                 type="text"
                                 v-decorator="['hotelName', { rules: [{ required: true, message: '请输入酒店名称' }] }]"
                                 placeholder="请输入酒店名称" v-model="hotelName"/>
                    </a-form-item>
                    <a-form-item class="searchDiv" label="所在商圈" colon="false">
                        <a-select class="searchInput" type="text"
                                  placeholder="请选择酒店所在商圈" @change="bizRegionChange">
                            <a-select-option v-for="item in bizRegionList" :key="item.key" :value="item.key" >
                                {{item.name}}({{item.key}})
                            </a-select-option>
                        </a-select>
                    </a-form-item >
                    <a-form-item style="margin-bottom: 0; align-items: center; width: 650px; text-align: left;" colon="false">
                        <a-checkbox-group :options="starList" v-model="starFilters">
                        </a-checkbox-group>
                    </a-form-item>
                    <a-form-item style="margin-right: 0; margin-bottom: 0">
                        <a-button @click="searchHotel" type="primary" class="searchButton">搜索</a-button>
                    </a-form-item>
                </a-form>
<!--            </a-layout-sider>-->
<!--            <a-layout-content>-->
                <div class="hotelList">
                    <a-layout>
                        <a-layout-header v-if="search" style="background-color: transparent; padding: 0">
                            <a-menu v-model="currentOrder" mode="horizontal" :default-selected-keys="['grade']" @click="sortOrderChange">
                                <template v-for="item in sortOrderList">
                                    <a-menu-item v-if="!item.children" :key="item.key">
                                        <a-icon type="mail"/><span>{{item.title}}</span>
                                    </a-menu-item>
                                    <a-sub-menu v-else :key="item.key">
                                        <span slot="title"><a-icon type="mail"/><span>{{item.title}}</span></span>
                                        <a-menu-item v-for="i in item.children" :key="i.key">
                                            {{i.title}}
                                        </a-menu-item>
                                    </a-sub-menu>
                                </template>
                            </a-menu>
                        </a-layout-header>
                        <a-layout-content style="min-width: 800px">
                            <a-spin :spinning="hotelListLoading">
                                <div class="card-wrapper">

                                    <div v-if="search">
                                        <HotelCard  :hotel="item" v-for="item in resultHotel" :key="item.index" @click.native="jumpToDetails(item.id)"></HotelCard>
                                        <div v-for="item in emptyBox" :key="item.name" class="emptyBox ant-col-xs-7 ant-col-lg-5 ant-col-xxl-3">
                                        </div>
                                    </div>
                                    <div v-else>
                                        <HotelCard :hotel="item" v-for="item in hotelList" :key="item.index" @click.native="jumpToDetails(item.id)"></HotelCard>
                                        <div v-for="item in emptyBox" :key="item.name" class="emptyBox ant-col-xs-7 ant-col-lg-5 ant-col-xxl-3">
                                        </div>
                                    </div>
                                </div>
                            </a-spin>
                        </a-layout-content>
                        <a-layout-footer>
                            <a-pagination showQuickJumper :total="hotelList.totalElements" :defaultCurrent="1" @change="pageChange"></a-pagination>
                        </a-layout-footer>
                    </a-layout>
                </div>
<!--            </a-layout-content>-->
        </a-layout>
    </div>
</template>
<script>
import HotelCard from './components/hotelCard'
import { mapGetters, mapActions, mapMutations } from 'vuex'
const bizRegionList = [
    {
        name: '全部',
        key: 'All',
    },
    {
        name: '西单',
        key: 'XiDan',
    }]

const starList = [
    {
        label: "五星级",
        value: "Five",
        num: 5,
    },
    {
        label: "四星级",
        value: "Four",
        num: 4,
    },
    {
        label: "三星级",
        value: "Three",
        num: 3,
    },
    {
        label: "二星级",
        value: "Two",
        num: 2,
    },
    {
        label: "一星级",
        value: "One",
        num: 1,
    },]

const sortOrderList = [
    {
        key: 'grade',
        title: '按评分排序',
    },
    // {
    //     key: 'price',
    //     title: '按价格排序',
    //     children: [
    //         {
    //             key: 'priceAscending',
    //             title: '按星级低->高',
    //         },
    //         {
    //             key: 'priceDescending',
    //             title: '按星级高->低',
    //         }
    //     ]
    // },
    {
        key: 'hotelStar',
        title: '按星级排序',
        // showedTitle: '按星级排序',
        children: [
            {
                key: 'hotelStarAscending',
                title: '按星级低->高',
            },
            {
                key: 'hotelStarDescending',
                title: '按星级高->低',
            }
        ]
    }]

export default {
  name: 'home',
  components: {
      HotelCard,
  },
  data(){
    return{
        emptyBox: [{ name: 'box1' }, { name: 'box2'}, {name: 'box3'}],
        hotelName: "",
        bizRegion: "",
        resultHotel: [],
        bizRegionList,
        starList,
        starFilters: ["Five", "Four", "Three", "Two", "One"],
        search: false,
        currentOrder: ["grade"],
        sortOrderList,
    }
  },
  async mounted() {
    await this.getHotelList();
    this.search = false;
  },
  computed: {
    ...mapGetters([
      'hotelList',
      'hotelListLoading'
    ]),
  },
  methods: {
    ...mapMutations([
      'set_hotelListParams',
      'set_hotelListLoading'
    ]),
    ...mapActions([
      'getHotelList'
    ]),
    pageChange(page, pageSize) {
      const data = {
        pageNo: page - 1
      }
      this.set_hotelListParams(data)
      this.set_hotelListLoading(true)
      this.getHotelList()
    },
    jumpToDetails(id){
      this.$router.push({ name: 'hotelDetail', params: { hotelId: id }})
    },

      searchHotel(){
        console.log(this.hotelList);
        console.log(this.bizRegion);
        this.search = true;
        let hotels = [];
        hotels = this.hotelList.filter((h) =>
            h.name.indexOf(this.hotelName) !== -1 &&
            (h.bizRegion === this.bizRegion || this.bizRegion === 'All') &&
            this.starFilters.indexOf(h.hotelStar) !== -1);
        console.log(hotels);
        this.resultHotel = hotels;
        console.log(this.starFilters);
        return hotels;
      },

      bizRegionChange(value){
        console.log(value);
        this.bizRegion = value;
      },

      sortOrderChange(e){
          console.log(e);
          let order = e.key;
          this.resultHotel.sort((h1, h2) => {
            if (order === ["grade"]){
                return h2.rate - h1.rate;
            }
            // else if (order === ['price']){
            //     return h2.
            // }
            else if (order === ['hotelStarDescending']){
                let s1 = this.starList.find((e) => e.value === h1.hotelStar);
                let s2 = this.starList.find((e) => e.value === h2.hotelStar);
                return s2.num - s1.num;
            }
            else if (order === ['hotelStarAscending']){
                let s1 = this.starList.find((e) => e.value === h1.hotelStar);
                let s2 = this.starList.find((e) => e.value === h2.hotelStar);
                return s1.num - s2.num;
            }
        });

      }
  }
}
</script>
<style scoped lang="less">
  .hotelList {
    text-align: center;
      display: flex;
      flex-wrap: wrap;
      overflow: inherit;
      box-lines: multiple;
      /*padding: 50px 0;*/ //上下的padding删掉
    .emptyBox {
      height: 0;
      margin: 10px 10px
    }
    .card-wrapper{
      display: flex;
      /*justify-content: space-around;*/
      flex-wrap: wrap;
      flex-grow: 3;
      min-height: 800px
    }
    .card-wrapper .card-item {
      margin: 30px;
      position: relative;
      height: 188px;
    }
  }
  .searchInput{
      text-align: left;
      margin: 0px 0px;
      font-size: 12px;
      width: 180px;
      height: 100%;
  }
  .searchDiv{
      display: flex;
      margin: 3px 0px;
      /*width: 33%;*/
      justify-content: center;
      align-items: center;
  }
  .searchButton{
      margin: 10px 20px 10px 20px;
  }
</style>