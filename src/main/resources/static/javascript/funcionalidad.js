/*Funcionalidad general*/
$(document).ready(function () {
    $("#loading-screen").css("display", "none");
    $("#screen").css("display", "block");

    loadProfile();
    loadServers();
    msgSize();
    sendMsgWithEnter();
    
    setup();
    contadorAfk = setInterval(ctrlTiempo, 6000);

    $(".pulsador").click(function () {
        $(this).prev().toggleClass("flip");
    });

    setTimeout(function () {
        $("#pot").toggleClass("invis")
    }, 66000);
    //$("#pot").toggleClass("invis");
});
function msgSize() {
    var height = $(window).height();

//                $("#size").height(height);
    $("#nabar-v").height(height - 10);
    $("#chat").height(height * 0.6);
//                $("#cuerpo").height(height);
    $("#userServers-v").height(height * 0.4);
    $('#contentChat').height(height * 0.6);
    $('#contentServer').height(height * 0.6);
}
function loadProfile() {
    $.ajax({
        url: "/profile/getUser",
        success: function (pHtml) {

            $("#user-profile").html('<span class="name tips" title="Mas información">' + pHtml.username + '</span>');
            $("#user-status").html('<span class="avatar tips" style="position:absolute" title="Modifica tu estado">' +
                    '<img alt="Avatar" class="avatar" src="/profile/avatar?url=' + pHtml.avatar + '">' +
                    '<span class="status-2 ' + pHtml.status + '"></span>' +
                    '</span>');

            $("#user-profile-v").html('<span class="name tips" title="Mas información">' + pHtml.username + '</span>');
            $("#user-status-v").html('<span class="avatar tips" style="position:absolute;left: 30%;" title="Modifica tu estado">' +
                    '<img alt="Avatar" class="avatar" src="/profile/avatar?url=' + pHtml.avatar + '">' +
                    '<span class="status-2 ' + pHtml.status + '"></span>' +
                    '</span>');
        },
        error: function (xhr, status, error) {
            console.log(xhr.responseText);
            window.location = "/logout";
        }
    });
}
function editStatus(status) {
    $.ajax({
        url: "/profile/statusDrop",
        data: {
            status: status
        },
        success: function (pHtml) {
            loadProfile();
        },
        error: function (xhr, status, error) {
            console.log(xhr.responseText);
        }
    });
}
function editProfile() {
    $.ajax({
        url: "/profile/edit",
        success: function (pHtml) {
            bootbox.dialog({
                title: 'Editar perfil',
                size: "large",
                message: pHtml
            })
        },
        error: function (xhr, status, error) {
            console.log(xhr.responseText);
        }
    });
}
function loadServers() {
    //userServers
    $.ajax({
        type: "POST",
        url: "/server/byUser",
        success: function (pJson) {
            //loadProfile();
            //console.log(pJson);
            $("#userServers").html("");
            $("#userServers-v").html("");
            var salida = " ";
            var salida2 = " ";
            for (x of pJson) {
                var URLactual = location.href;
//                            console.log(location.href)
                var id = URLactual.split("=");

                if (id[1] == x.id) {
                    salida += "<a href=/server/one?idServer=" + x.id + " id='" + x.id + "' style='border-top: 2px solid white' class='tips' title='" + x.name + "'>" +
                            "<img class='avatar' src=/profile/avatar?url=" + x.avatar + " style='margin: 6px;'>" +
                            "</a>";
                    salida2 += "<a href=/server/one?idServer=" + x.id + " id='" + x.id + "' style='border-left: 2px solid white' class='tips' title='" + x.name + "'>" +
                            "<img class='avatar' src=/profile/avatar?url=" + x.avatar + " style='margin: 6px;'>" +
                            "</a>";
                } else {
                    salida += "<a href=/server/one?idServer=" + x.id + " id='" + x.id + "' class='tips' title='" + x.name + "'>" +
                            "<img class='avatar' src=/profile/avatar?url=" + x.avatar + " style='margin: 6px;'>" +
                            "</a>";
                    salida2 += "<a href=/server/one?idServer=" + x.id + " id='" + x.id + "' class='tips' title='" + x.name + "'>" +
                            "<img class='avatar' src=/profile/avatar?url=" + x.avatar + " style='margin: 6px;'>" +
                            "</a>";
                }
            }
            //console.log(salida);
            $("#userServers").append(salida);
            $("#userServers-v").append(salida2);
        },
        error: function (xhr, status, error) {
            alert(xhr.responseText);
        }
    });
}
function servidor() {
    $.ajax({
        type: "POST",
        url: "/server/allServers",
        success: function (pHtml) {
            $("body").html(pHtml);
        },
        error: function (xhr, status, error) {
            console.log(xhr.responseText);
        }
    });
}

function chat(idCanal, channelName) {
    var userId = "";
    $.ajax({
        url: "/profile/getUser",
        success: function (pHtml) {
            userId = pHtml
        }
    });

    $.ajax({
        type: "POST",
        url: "/msg/channel/" + idCanal,
        success: function (pJson) {
            console.log(pJson);
            $("#channel-name").html(channelName);
            $("#sendit").html("<input type='text' id='sendMsg' name='text' placeholder='Escribe un mensaje'>" +
                    "<a id='clickmsg' onclick=enviarMsgCanal(" + idCanal + ") class='tips text-white' title='Enviar Mensaje' ><i class='fnt-aws-size far fa-paper-plane'></i></a>"
                    + "<a id='clickmsgfile' onclick=enviarMsgFile('channel'," + idCanal + ") clas='tips text-white' title='Enviar Archivo'><i class='fnt-aws-size fas fa-paperclip'></i></a>");

            var salida = $("<div class='w-100'>").html("<div class='h3 mx-4 my-4 text-aling-center'>¡Te damos la bienvenida al canal!<br><br></div>");
//                        $("<tr>").html("<div class='h3 mx-4 my-4 text-aling-center'>¡Te damos la bienvenida al canal!<br><br></div>")

            $("#contentChat").html("");
            for (x of pJson) {
                console.log(x);
                if (x.user.username == userId.username) {
                    $("<div>").html('<div class="answer right mx-4 pb-4">' +
                            '<div class="avatar mb-4">' +
                            '<img src=/profile/avatar?url=' + x.user.avatar + ' alt="User name" width="40" height="40">' +
                            '<span class="status offline"></span>' +
                            '</div>' +
                            '<div class="name">' + x.user.nickName + '</div>' +
                            '<div class="text">' +
                            x.text +
                            '</div>' +
                            '</div>').appendTo(salida);

                } else {
                    $("<div>").html('<div class="answer left mx-4 pb-4">' +
                            '<div class="avatar mb-4">' +
                            '<img src=/profile/avatar?url=' + x.user.avatar + ' alt="User name" width="40" height="40">' +
                            '<span class="status offline"></span>' +
                            '</div>' +
                            '<div class="name">' + x.user.nickName + '</div>' +
                            '<div class="text">' +
                            x.text +
                            '</div>' +
                            '</div>').appendTo(salida);
//                            $("<tr>").html("<td><p><span class='mensaje'><img alt='Avatar' class='avatar-msg' src=/profile/avatar?url=" + x.user.avatar + " />  " + x.user.nickName + ":<br></span><span class='mensaje-2'> " + x.text + "</span></p></td>").appendTo(salida);   
                }
            }
//                        salida.appendTo("#contentChat");
            $("#contentChat").append(salida);

            $('#contentChat').scrollTop($('#contentChat').prop('scrollHeight'));
//                        setTimeout(function(){chat(idCanal)}, 20000);
        },
        error: function (xhr, status, error) {
            console.log(xhr.responseText);
        }
    });
}
function enviarMsgCanal(idCanal) {
    var msg = $("#sendMsg").val();
    if (msg.length > 0 && msg != " ") {

        //alert($("#sendMsg").val().length);
        $.ajax({
            type: "POST",
            url: "/msg/channel/sendmsg",
            data: {
                idChannel: idCanal, //Obtener id con las cookies del usuario 
                text: $("#sendMsg").val()
            },
            success: function (pJson) {
//                            console.log("Mensaje enviado");
                chat(idCanal);

                $('#contentChat').scrollTop($('#contentChat').prop('scrollHeight'));
            },
            error: function (xhr, status, error) {
                console.log(xhr.responseText);
            }
        });
    }
}
//Rueda mouse horizontal 
var item = document.getElementById("userServers");
window.addEventListener("wheel", function (e) {
    if (e.deltaY > 0)
        item.scrollLeft += 100;
    else
        item.scrollLeft -= 100;
});
function chatPrivado(idDestino, nameDestino) {
    var userId = "";
    $.ajax({
        url: "/profile/getUser",
        success: function (pHtml) {
            userId = pHtml
        }
    });

    $.ajax({
        type: "POST",
        url: "/msg/private/" + idDestino,
        success: function (pJson) {
            //console.log(pJson);
            $("#user-chat-name").html(nameDestino);
            $("#sendit").html("<input type='text' id='sendMsg' name='text' placeholder='Escribe un mensaje'>" +
                    "<a id='clickmsg' onclick=enviarMsgPrivado(" + idDestino + ") class='tips text-white' title='Enviar Mensaje' ><i class='fnt-aws-size far fa-paper-plane'></i></a>"
                    + "   <a id='clickmsgfile' onclick=enviarMsgFile('private'," + idDestino + ")class='tips text-white' title='Enviar Archivo' ><i class='fnt-aws-size fas fa-paperclip'></i></a>");
            var salida = $("<div class='w-100'>").html("<div class='h3 mx-4 my-4 text-aling-center'>¡Este es el comienzo de tus mensajes privados con @" + nameDestino + "!<br><br></div>")
//                                $("<tr>").html("<div class='h2 mx-2 my-2 pt-4 pl-3 text-aling-center'>¡Este es el comienzo de tus mensajes privados!<br><br></div>");
            $("#panelChat").html("");
            for (x of pJson) {
                if (x.user.username == userId.username) {
                    $("<div>").html('<div class="answer right mx-4 pb-4">' +
                            '<div class="avatar mb-4">' +
                            '<img src=/profile/avatar?url=' + x.user.avatar + ' alt="User name" width="40" height="40">' +
                            '<span class="status offline"></span>' +
                            '</div>' +
                            '<div class="name">' + x.user.nickName + '</div>' +
                            '<div class="text">' +
                            x.text +
                            '</div>' +
                            '</div>').appendTo(salida);
                } else {
                    $("<div>").html('<div class="answer left mx-4 pb-4">' +
                            '<div class="avatar mb-4">' +
                            '<img src=/profile/avatar?url=' + x.user.avatar + ' alt="User name" width="40" height="40">' +
                            '<span class="status offline"></span>' +
                            '</div>' +
                            '<div class="name">' + x.user.nickName + '</div>' +
                            '<div class="text">' +
                            x.text +
                            '</div>' +
                            '</div>').appendTo(salida);
//                            $("<tr>").html("<td><p><span class='mensaje'><img alt='Avatar' class='avatar-msg' src=/profile/avatar?url=" + x.user.avatar + " />  " + x.user.nickName + ":<br></span><span class='mensaje-2'> " + x.text + "</span></p></td>").appendTo(salida);   
                }
//                            $("<tr>").html("<td><p><span class='mensaje'><img alt='Avatar' class='avatar-msg' src=/profile/avatar?url=" + x.user.avatar + " />  " + x.user.nickName + ":<br></span><span class='mensaje-2'> " + x.text + "</span></p></td>").appendTo(salida);
//                            $("<tr>").html('<div class="answer left<div class="avatar"><img alt="Avatar"  src=/profile/avatar?url=' + x.user.avatar + ' /><div class="status offline"></div></div><div class="name">'+ x.user.nickName +'</div><div class="text">'+ x.text + '</div><div class="time">5 min ago</div></div>');
            }
//                        salida.appendTo("#contentChat");
            salida.appendTo("#panelChat");
            $('#contentChat').scrollTop($('#contentChat').prop('scrollHeight'));
//                        setTimeout(function(){chatPrivado(idDestino)}, 20000);
        },
        error: function (xhr, status, error) {
            console.log(xhr.responseText);
        }
    });
}

function enviarMsgPrivado(idDestino) {
    var msg = $("#sendMsg").val();
    if (msg.length > 0 && msg != " ") {
        $.ajax({
            type: "POST",
            url: "/msg/private/sendmsg",
            data: {
                idUserDestiny: idDestino,
                text: $("#sendMsg").val()
            },
            success: function (pJson) {
                //console.log("Mensaje enviado");
                chatPrivado(idDestino);

                $('#contentChat').scrollTop($('#contentChat').prop('scrollHeight'));
            },
            error: function (xhr, status, error) {
                console.log(xhr.responseText);
            }
        });
    }
}

function amigos() {

    $.ajax({
        type: "POST",
        url: "/friends/friendsFilter",
        data: {
            username: $("#buscaAmigo").val()

        },
        success: function (pHtml) {
            $("#obtenAmigo").html(pHtml);
        },
        error: function (xhr, status, error) {
            console.log(xhr.responseText);
        }
    });
}
function editCategory(idServer, idCategory) {
    $.ajax({
        url: "/category/form",
        data: {
            idServer: idServer,
            idCategory: idCategory
        },
        success: function (html) {
            bootbox.dialog({
                title: 'Categoría',
                message: html
            })
        },
        error: function (xhr, status, error) {
            console.log(xhr.responseText);
        }
    })
}

function editChannel(idCategory, idChannel) {
    $.ajax({
        url: "/channel/form",
        data: {
            idCategory: idCategory,
            idChannel: idChannel
        },
        success: function (html) {
            bootbox.dialog({
                title: 'Canal',
                message: html
            })
        },
        error: function (xhr, status, error) {
            console.log(xhr.responseText);
        }
    })
}
function editServer(idServer) {
    $.ajax({
        url: "/server/form",
        data: {
            idServer: idServer
        },
        success: function (html) {
            bootbox.dialog({
                title: 'Servidor',
                message: html
            })
        },
        error: function (xhr, status, error) {
            console.log(xhr.responseText);
        }
    })
}
function confirmServer(idServer, title, url) {
    bootbox.confirm({
        message: title,
        callback: function (result) {
            if (result) {
                $.ajax({
                    url: url, //"/usr/leaveServer",
                    data: {
                        idServer: idServer
                    },
                    success: function (html) {
                        window.location = "/home";
                    },
                    error: function (xhr, status, error) {
                        console.log(xhr.responseText);
                    }
                })
            }
        }
    })
}
$('.option-gear').hover(function () {
    $('.gear').css({
        "display": "block"
    });
}, function () {
    $('.gear').css({
        "display": "none"
    });
});
function hBarOn() {//12 H
    $("#nabar-v").css("display", "none");
    $("#nabar-h").css("display", "block");

    $("#cuerpo").removeClass("col-11");
    $("#cuerpo").addClass("col-12");

    msgSize();
}
function vBarOn() {//11 V
    $("#nabar-h").css("display", "none");
    $("#nabar-v").css("display", "block");

    $("#cuerpo").removeClass("col-12");
    $("#cuerpo").addClass("col-11");

    msgSize();
}
function sendMsgWithEnter() {
    $("#sendit").keyup(function (event) {
        if (event.keyCode === 13) {
            $("#clickmsg").click();
//                        alert("va");
        }
    });
}
function removeFriend(idFriend){
    bootbox.confirm({
        message: "¿Estas seguro que deseas eliminar a este amigo?",
        callback: function (result) {
            if (result) {
                $.ajax({
                    type: "GET",
                    url: "/friends/removeFriend?idFriend=" + idFriend,
                    success: function (pJson) {

                    },
                    error: function (xhr, status, error) {
                        console.log(xhr.responseText);
                    }
                });
            }
        }
    })
}
function enviarMsgFile(type,id){
    var msg = '<form enctype="multipart/form-data" action="/msg/'+type+'/sendFile/" method="post">'+ 
       ' <input  type="file" id="archivo" name="archivo"/>'+
       ' <input  type="hidden" id="id" name="id"/>'+
       ' <input  type="submit" value="Enviar"/>'+
   ' </form>';
    
    bootbox.confirm({
        message: msg,
        callback: function (result) {
            if (result) {
//                $.ajax({
//                    type: "POST",
//                    url: "/msg/"+type+"/sendFile/",
//                    data: {
//                        archivo: $("#archivo").val(),
//                        id: id
//                    },
//                    success: function (html) {
////                        window.location = "/home";
//                    },
//                    error: function (xhr, status, error) {
//                        console.log(xhr.responseText);
//                    }
//                })
            }
        }
    })
}

/*ToolTips*/
$(function () {
    $(".tips").tooltip();
//                $(".tips").tooltip();
});

/*Timer*/
var contadorAfk = 0;



function ctrlTiempo() {

    contadorAfk++;
    //console.log(contadorAfk);

    if (contadorAfk > 5) {
        contadorAfk = 0;
        //alert("Estas AFK");
        editStatus('ausente');
//                  $("#pot").toggleClass("invis");
    }
}

function resetearTimer() {
    contadorAfk = 0;
}

function setup() {
    this.addEventListener("mousemove", resetearTimer, false);
    this.addEventListener("mousedown", resetearTimer, false);
    this.addEventListener("keypress", resetearTimer, false);
    this.addEventListener("DOMMouseScroll", resetearTimer, false);
    this.addEventListener("mousewheel", resetearTimer, false);
    this.addEventListener("touchmove", resetearTimer, false);
    this.addEventListener("MSPointerMove", resetearTimer, false);
}