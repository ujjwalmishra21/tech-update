(this["webpackJsonptech-update-react"]=this["webpackJsonptech-update-react"]||[]).push([[6],{170:function(t,e,a){"use strict";var n=a(0),r=a.n(n),o=a(155),c=a(187),i=Object(o.a)((function(t){return{root:{display:"flex","& > * + *":{marginLeft:t.spacing(2)}}}}));e.a=function(t){var e=i();return r.a.createElement("div",{className:e.root},r.a.createElement(c.a,null))}},178:function(t,e,a){},217:function(t,e,a){"use strict";a.r(e);var n=a(177),r=a(40),o=a(41),c=a(43),i=a(42),s=a(0),l=a.n(s),u=a(44),d=(a(178),a(179)),p=a.n(d),f=a(181),h=a(75),m=a(155),b=a(208),g=a(209),k=a(210),v=a(162),E=a(50),j=a(185),O=a.n(j),w=a(184),y=a.n(w),x=a(186),D=a.n(x),I=a(182),N=a.n(I),C=a(9),S=a(35),A=a(58),U=Object(m.a)((function(t){return{root:{width:310,marginRight:25,marginBottom:25,boxShadow:"1px 1px 0.5em"},cardHeader:{width:"100%",whiteSpace:"nowrap"},media:{height:0,paddingTop:"56.25%"},expand:{transform:"rotate(0deg)",marginLeft:"auto",transition:t.transitions.create("transform",{duration:t.transitions.duration.shortest})},expandOpen:{transform:"rotate(180deg)"},avatar:{backgroundColor:E.a[500]}}})),H=Object(u.b)((function(t){return{username:t.auth.username,token:t.auth.token,temp:t.data.temp}}),(function(t){return{likeUnlikePost:function(e,a){return t(A.e(e,a))}}}))((function(t){var e=U(),a=!1;t.likes.length>0&&t.likes.forEach((function(e){e.username===t.username&&(a=!0)}));var n=Object(s.useState)(a),r=Object(h.a)(n,2),o=r[0],c=r[1],i=(Object(C.g)(),function(){var e=Object(f.a)(p.a.mark((function e(){return p.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:navigator.share&&navigator.share({url:"".concat(window.location.href,"posts/").concat(t.postId),title:t.title}).then((function(){console.log("Data share successfully")})).catch((function(t){console.log("Unable to share data")}));case 1:case"end":return e.stop()}}),e)})));return function(){return e.apply(this,arguments)}}());return Object(s.useEffect)((function(){t.temp&&t.temp.id===t.postId&&c(!o)}),[t.temp]),l.a.createElement("div",null,l.a.createElement(b.a,{className:e.root},l.a.createElement(g.a,{className:e.cardHeader,title:t.title,subheader:N()(t.createdAt).fromNow()}),l.a.createElement(S.b,{to:"/posts/".concat(t.postId)},l.a.createElement("img",{src:"data:image/jpeg;base64,".concat(t.files[0].fileData),height:"200px",width:"300px"})),l.a.createElement(k.a,{disableSpacing:!0},l.a.createElement(v.a,{"aria-label":"add to favorites",onClick:function(){t.likeUnlikePost(t.token,t.postId)}},o?l.a.createElement(O.a,{color:"secondary"}):l.a.createElement(y.a,null)),l.a.createElement(v.a,{"aria-label":"share",onClick:i},l.a.createElement(D.a,null)))))})),J=a(170),L=function(t){Object(c.a)(a,t);var e=Object(i.a)(a);function a(){return Object(r.a)(this,a),e.apply(this,arguments)}return Object(o.a)(a,[{key:"componentDidMount",value:function(){this.props.fetchData(this.props.token)}},{key:"render",value:function(){var t=[];if(this.props.loading)t=l.a.createElement(J.a,null);else if(this.props.data&&this.props.data.length>0){var e,a=Object(n.a)(this.props.data);try{for(a.s();!(e=a.n()).done;){var r=e.value,o=l.a.createElement(H,{key:r.id,postId:r.id,title:r.title,content:r.title,files:r.files,createdAt:r.createdAt,likes:r.likes});t.unshift(o)}}catch(c){a.e(c)}finally{a.f()}}return l.a.createElement("div",{className:"posts-section"},t)}}]),a}(s.Component);e.default=Object(u.b)((function(t){return{loading:t.data.loading,data:t.data.data,error:t.data.error,token:t.auth.token}}),(function(t){return{fetchData:function(e){return t(A.c(e))}}}))(L)}}]);
//# sourceMappingURL=6.71df2027.chunk.js.map