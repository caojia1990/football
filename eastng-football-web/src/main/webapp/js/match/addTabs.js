/*
	点击树菜单叶子节点添加一个tab页，动态生成datagrid展示比赛信息
	caojia
*/
function addTab(node){
	 if ($('#tabsMain').tabs('exists', node.text)){
		 $('#tabsMain').tabs('select', node.text);
	 }else{
		$('#tabsMain').tabs('add',{
		    title:node.text,
		    closable:true
		});
		
		var currentTabPanel = $("#tabsMain").tabs('getSelected');
		var dynamicTable = $('<table id="dg'+node.id+'" title="比赛信息" style="width: 850px; height: 340px"'+
						'data-options="rownumbers:true,singleSelect:true,pagination:true">')
		//$.parser.parse(dynamicTable);
		$(currentTabPanel).html(dynamicTable);	
		//$.parser.parse(currentTabPanel);
						
		var paramVO1 = {
						leagueNo:node.id
						};
		dynamicTable.datagrid({
		    url:'../queryMatch',
		    queryParams: {
		    	paramVO:JSON.stringify(paramVO1)
			},
		    method:'post',
		    columns:[[
				{field:'matchTime',title:'开赛时间',width:150},
				{field:'leagueShortName',title:'赛事名称',width:80},
				{field:'round',title:'轮次',width:40},
				{field:'hostShortName',title:'主队',width:100},
				{field:'score',title:'比分',width:60},
				{field:'guestShortName',title:'客队',width:100},
				{field:'name',title:'主胜',width:60},
				{field:'name',title:'平局',width:60},
				{field:'price',title:'主负',width:60,align:'right'}
		    ]]
		});
		
		dynamicTable.datagrid({
			onDblClickRow: function(index,row){
				$('#win').window({
				    width:600,
				    height:400,
				    modal:true
				});
				$('#hostName').text(row.hostShortName);
				$('#score').text(row.hostGoal+":"+row.guestGoal);
				$('#GuestName').text(row.guestShortName);
				$('#halfScore').text(row.halfTimeHostGoal+":"+row.halfTimeGuestGoal);
				$('#win').window('open');
			}
		});
	}
}