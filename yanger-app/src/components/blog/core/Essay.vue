<template>
    <el-row id="content" class="row" style="margin: 0px;">
		<div id="content_left">
			<div id="article">
				<h2 class="title-h2">生活很精彩&nbsp;&nbsp;&nbsp;每一天都值得被记录</h2>
                <div class="content_study_note"  v-for="(essay, index) in essayData.data.essays" :key="essay.index">
                    <div class="note_left">
                        <img alt="" src="static/img/img.jpg">
                    </div>
                    <div class="note_right">
                        <h4><a href="" title="文章标题">{{essay.title}}</a></h4>
                        <p class="note_c">{{essay.summary}}</p>
                        <autor :createDate='essay.createDate' :likes='essay.likes' :commons='essay.commons' :views='essay.views'></autor>
                    </div>
                </div>
				<div id="page_bar">
					<el-pagination @current-change="handleCurrentChange" 
                        :current-page.sync="essayData.page.currentPage" :page-size="essayData.page.size" 
                        layout="total,jumper, prev, pager, next" :total="essayData.page.total">
                    </el-pagination>
				</div>
			</div>
		</div>
		<div id="content_right">
			<!-- 网站登录模块 -->
			<div id="dete_line">
				<h3>时间线</h3>
				<el-tabs tab-position="left">
					<el-tab-pane label="2017年">
						<ul>
							<li>1月（3）</li>
							<li>2月（3）</li>
                            <li>3月（31）</li>
                            <li>4月（3）</li>
                            <li>5月（3）</li>
                            <li>6月（3）</li>
                            <li>7月（3）</li>
                            <li>8月（3）</li>
                            <li>9月（31）</li>
                            <li>10月（3）</li>
                            <li>11月（23）</li>
                            <li>12月（332）</li>
						</ul>
					</el-tab-pane>
					<el-tab-pane label="2018年">
						<ul>
							<li>1月（3）</li>
							<li>2月（5）</li>
                            <li>3月（31）</li>
                            <li>4月（3）</li>
                            <li>5月（7）</li>
                            <li>6月（9）</li>
                            <li>7月（3）</li>
						</ul>
					</el-tab-pane>
				</el-tabs>
			</div>
			<div  id="note_hot">
				<h3>推荐日志</h3>
				<ul  v-for="(hot, index) in essayData.data.hots" :key="hot.index">
					<li>
						<a href="" :title="hot.id" target="_blank">{{hot.title}}</a>
					</li>
				</ul>
			</div>
		</div>
	</el-row >
</template>

<script>
	import autor from '../common/Autor';
	export default {
		data() {
			return {
				essayData: {
					data: {
						essays: [
							{
								createDate: "1028-09-09",
								title: "2二十大二恶大的撒",
								summary:
									"魔力数据dsds的四大四大阿魔力数据dsds的四大四大魔力数据dsds的四大四大魔力数据dsds的四大四大魔力数据dsds的四大四大达阿斯顿撒旦撒旦阿达",
								likes: 18,
								commons: 299,
								views: 333
							},
							{
								createDate: "1028-09-09",
								title: "恶风大大的撒",
								summary: "魔力数据斯顿撒旦撒旦阿达",
								likes: 18,
								commons: 299,
								views: 333
							},
							{
								createDate: "1028-09-09",
								title: "十大大的撒",
								summary:
								"魔力数据dsds的四大四大阿魔力数据dsds的四大四大魔力数据sds的四大四大阿魔力数据ds\
											s的四大四大魔力数据dsds的sds的四大四大阿魔力数据dsds的四大四大魔力数据dsds的sds的四大四\
											数据dsds的四大四大魔力数数据dsds的四大四大魔力数数据dsds的四大四大魔力数大阿魔力数据dsds的四大四大魔力数据dsds\
											的dsds的四大四大魔力数据dsds的四大四大魔力数据dsds的四大四大达阿斯顿数据dsds的四大四大魔力数数据dsds的四大四大魔力数数据dsds的四大四大魔力数撒旦撒旦阿达",
								likes: 18,
								commons: 299,
								views: 333
							},
							{
								createDate: "1028-09-09",
								title: "十大大的撒",
								summary:
								"魔力数据dsds的四大四大阿魔力数据dsds的四大四大魔力数据sds的四大四大阿魔力数据ds\
											s的四大四大魔力数据dsds的sds的四大四大阿魔力数据dsds的四大四大魔力数据dsds的sds的四大四\
											数据dsds的四大四大魔力数数据dsds的四大四大魔力数数据dsds的四大四大魔力数大阿魔力数据dsds的四大四大魔力数据dsds\
											的dsds的四大四大魔力数据dsds的四大四大魔力数据dsds的四大四大达阿斯顿数据dsds的四大四大魔力数数据dsds的四大四大魔力数数据dsds的四大四大魔力数撒旦撒旦阿达",
								likes: 18,
								commons: 299,
								views: 333
							},
							{
								createDate: "1028-09-09",
								title: "十大大的撒",
								summary:
								"魔力数据dsds的四大四大阿魔力数据dsds的四大四大魔力数据sds的四大四大阿魔力数据ds\
											s的四大四大魔力数据dsds的sds的四大四大阿魔力数据dsds的四大四大魔力数据dsds的sds的四大四\
											数据dsds的四大四大魔力数数据dsds的四大四大魔力数数据dsds的四大四大魔力数大阿魔力数据dsds的四大四大魔力数据dsds\
											的dsds的四大四大魔力数据dsds的四大四大魔力数据dsds的四大四大达阿斯顿数据dsds的四大四大魔力数数据dsds的四大四大魔力数数据dsds的四大四大魔力数撒旦撒旦阿达",
								likes: 18,
								commons: 299,
								views: 333
							}
						],
						hots: [
							{
								id: 1,
								title: '山东省地方'
							},
							{
								id: 1,
								title: '多的是矫情的人'
							},
							{
								id: 1,
								title: '一个人的夜我的心应该放在哪里'
							},{
								id: 1,
								title: '礼物不需要太贵'
							},{
								id: 1,
								title: '胜多负少都很开放'
							}
						]
					},
					page: {
						currentPage: 1,
						size: 5,
						total: 20
					},
					queryValue: ''
				}
			};
		},
		methods: {
			handleCurrentChange(val) {
				console.log(`当前页: ${val}`);
			}
		},
		components: {
			autor
		}
	};
</script>

<style scoped>
	#content {
		width: 1000px;
		margin: 0 auto; 
		margin-top:8px;
	}
	#article {
		margin-top: -20px;
		width: 700px;
		max-height: 933px;
		min-height: 723px;
		background-color: #f9f6a9;
	}
	#content_left {
		width: 700px;
		height: 100%;
		float: left;
		margin-top: 24px;
	}
	#content_right {
		width: 280px;
		height: 100%;
		float: right;
		margin-top:3px;
		background-color: #fefefe;
	}
	#content_left .title-h2{
		width: 100%;
		height: 50px;
		font-size: 23px;
		text-align: center;
		padding-top: 18px;
		font-weight: 500;
	}

	/* ---------------文章列表 --------------- */
	.content_study_note {
		border-bottom: #BFAB86 1px solid;
		overflow: hidden;
		background-color: #fffce8;
	}
	.note_left {
		width: 150px;
		height: 136px;
		float: left;
		margin: 20px 0 10px 2px;
	}
	.note_left img{
		width: 90%;
		height: 90%;
	}
	.note_c {
		color: #888;
		height: 80px;
		margin-bottom: 15px;
		margin-right: 10px;
		overflow: hidden;
		font-size: 14px;
		margin-top: 10px;
		line-height: 21px;
	}
	.note_right {
		width: 75%;
		height: 136px;
		float: right;
		margin: 5px 0 10px 2px;
	}
	.note_right h4{
		font-size: 16px;
		text-align: center;
		font-weight: 500;
	}
	.note_right h4 a {
		color: #72afd2;
	}

	/* ----------------时间线-------------- */
	#dete_line {
		margin-bottom: 30px;
	}
	#dete_line h3 {
		line-height: 40px;
		color: #38485A;
		font-size: 18px;
		border-bottom: 1px solid #ff3300;
		text-align: center;
		height: 40px;
		margin-bottom: 16px;
	}
	#dete_line .el-tabs {
		height: 300px;
	}
	#dete_line li {
		font-size: 13px;
		line-height: 25px;
		margin-left: 30px;
	}
	#dete_line li:hover {
		font-size: 13px;
		line-height: 25px;
		margin-left: 30px;
		color: #409EFF;
		cursor: pointer;
	}

	/*------------------ 推荐日志------------------*/
	#note_hot h3 {
		line-height: 40px;
		color: #38485a;
		font-size: 18px;
		border-bottom: 1px solid #ff3300;
		text-align: center;
		height: 40px;
		margin-bottom: 16px;
		font-weight: 500;
	}
	#note_hot ul li {
		line-height: 26px;
		list-style: square;
		font-size: 15px;
		margin-left: 25px;
		font-size: 14px;
	}
	#note_hot ul li a {
		color: #72afd2;
	}
</style>

<style>
	/* ----------------分页组件------------- */
	#article .el-pagination .btn-prev {
		margin-left: 10%;
	}
	#article .el-pagination .el-input__inner, .btn-prev, .btn-next {
		background-color: transparent !important;
	}
	#article .el-pagination .el-pager li {
		background-color: transparent;
	}
</style>