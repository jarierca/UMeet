INSERT INTO `users` (`id`, `avatar`, `email`, `nick_name`, `pass`, `status`, `username`) VALUES ('1', 'a', 'ellie.kautzer@example.net', 'Darren Johns', 'Reinger', 'conectado', 'cboyer');
INSERT INTO `users` (`id`, `avatar`, `email`, `nick_name`, `pass`, `status`, `username`) VALUES ('2', 'a', 'cdach@example.net', 'Jerrell O\'Keefe', 'Mueller', 'conectado', 'talon58');
INSERT INTO `users` (`id`, `avatar`, `email`, `nick_name`, `pass`, `status`, `username`) VALUES ('3', 'a', 'price.annetta@example.com', 'Prof. Fletcher Schmidt Sr.', 'Witting', 'conectado', 'ernser.mason');
INSERT INTO `users` (`id`, `avatar`, `email`, `nick_name`, `pass`, `status`, `username`) VALUES ('4', 'b', 'turner.dubuque@example.org', 'Annette McClure', 'Quitzon', 'ocupado', 'davion05');
INSERT INTO `users` (`id`, `avatar`, `email`, `nick_name`, `pass`, `status`, `username`) VALUES ('5', 'a', 'muller.nathan@example.org', 'Mr. Nathanael Weber I', 'Toy', 'desconectado', 'reinger.bobby');

INSERT INTO `friends` (`id`, `status`, `id_user1`, `id_user2`) VALUES ('1', 'aceptado', '1', '2');
INSERT INTO `friends` (`id`, `status`, `id_user1`, `id_user2`) VALUES ('2', 'aceptado', '2', '3');
INSERT INTO `friends` (`id`, `status`, `id_user1`, `id_user2`) VALUES ('3', 'invitado', '3', '1');
INSERT INTO `friends` (`id`, `status`, `id_user1`, `id_user2`) VALUES ('4', 'invitado', '4', '5');
INSERT INTO `friends` (`id`, `status`, `id_user1`, `id_user2`) VALUES ('5', 'invitado', '5', '2');

INSERT INTO `servers` (`id`, `avatar`, `description`, `name`, `status`) VALUES ('1', '8', 'Saepe amet dolores quidem ex ut. Dolorum quaerat veniam nulla aspernatur.', 'Blake Gulgowski', 'activo');
INSERT INTO `servers` (`id`, `avatar`, `description`, `name`, `status`) VALUES ('2', '1', 'Beatae dicta dolore fuga earum necessitatibus. Aut voluptas perspiciatis ut est optio. At architecto qui quam aut eius. Aliquam ut et sunt numquam.', 'Don Rempel', 'activo');
INSERT INTO `servers` (`id`, `avatar`, `description`, `name`, `status`) VALUES ('3', '5', 'Quis aperiam quia debitis maxime harum doloremque nam beatae. Ea ipsa non excepturi quibusdam quisquam perferendis perferendis quisquam. Eveniet vel consequatur delectus beatae similique.', 'Kaitlin Gislason IV', 'bloqueado');
INSERT INTO `servers` (`id`, `avatar`, `description`, `name`, `status`) VALUES ('4', '3', 'Dolore fugit magni maiores ab voluptate. Temporibus pariatur repudiandae aut. Sint rerum repudiandae sunt nemo pariatur tempora sapiente. Rerum et ipsum sint aut quia dolorem incidunt. Modi labore non ut sunt veritatis illum vel.', 'Leonora Haag', 'activo');
INSERT INTO `servers` (`id`, `avatar`, `description`, `name`, `status`) VALUES ('5', '1', 'Est qui itaque ut qui dolor nemo illo laborum. Reiciendis a maiores ipsam dolore. Quo rerum enim cum repudiandae molestiae error. Et mollitia nulla eaque aperiam libero.', 'Mrs. Eva Dare Jr.', 'bloqueado');


INSERT INTO `categories` (`id`, `name`, `id_server`) VALUES ('1', 'Stephanie', '1');
INSERT INTO `categories` (`id`, `name`, `id_server`) VALUES ('2', 'Nona', '2');
INSERT INTO `categories` (`id`, `name`, `id_server`) VALUES ('3', 'Urban', '3');
INSERT INTO `categories` (`id`, `name`, `id_server`) VALUES ('4', 'Lera', '4');
INSERT INTO `categories` (`id`, `name`, `id_server`) VALUES ('5', 'Tressie', '5');


INSERT INTO `channels` (`id`, `name`, `id_category`) VALUES ('1', 'Macejkovic', '1');
INSERT INTO `channels` (`id`, `name`, `id_category`) VALUES ('2', 'Graham', '2');
INSERT INTO `channels` (`id`, `name`, `id_category`) VALUES ('3', 'Deckow', '3');
INSERT INTO `channels` (`id`, `name`, `id_category`) VALUES ('4', 'Kiehn', '4');
INSERT INTO `channels` (`id`, `name`, `id_category`) VALUES ('5', 'Prosacco', '5');

INSERT INTO `messages` (`id`, `name`, `text`, `id_channel`, `id_user`,`id_user_destiny`) VALUES ('1', 'ipsum', 'Qui qui aut deleniti temporibus suscipit et quis. Quidem qui dolores magni quisquam voluptatem quo ratione. Et eum perferendis rerum est quis. Enim est rerum debitis blanditiis beatae.', '1', '1',null);
INSERT INTO `messages` (`id`, `name`, `text`, `id_channel`, `id_user`,`id_user_destiny`) VALUES ('2', 'similique', 'Sed earum amet nulla repellat qui ratione occaecati. Assumenda voluptatem sunt tenetur quod. Rerum doloremque qui dolor sit. Mollitia dolores repudiandae ut nihil exercitationem hic molestias.', '2', '2',null);
INSERT INTO `messages` (`id`, `name`, `text`, `id_channel`, `id_user`,`id_user_destiny`) VALUES ('3', 'iste', 'Beatae et non voluptatum vero molestiae numquam mollitia. Iusto cupiditate nisi a eum et quia id. Odio quod vitae mollitia voluptas voluptatem itaque facilis.', '3', '3',null);
INSERT INTO `messages` (`id`, `name`, `text`, `id_channel`, `id_user`,`id_user_destiny`) VALUES ('4', 'odit', 'In exercitationem voluptatum hic velit et cumque consequatur doloribus. Beatae commodi rerum pariatur enim in ea itaque. Quo eum quis commodi deserunt. Qui quos adipisci voluptates libero.', '4', '4',null);
INSERT INTO `messages` (`id`, `name`, `text`, `id_channel`, `id_user`,`id_user_destiny`) VALUES ('5', 'ut', 'Odio fugiat est ut placeat impedit. Fugiat quibusdam accusantium consequatur consequatur. Beatae adipisci repellat fuga quam voluptas non. Ipsum ab illum dolorem.', '5', '5',null);
INSERT INTO `messages` (`id`, `name`, `text`, `id_channel`, `id_user`,`id_user_destiny`) VALUES ('6', 'ut', 'Odio fugiat est ut placeat impedit. Fugiat quibusdam accusantium consequatur consequatur. Beatae adipisci repellat fuga quam voluptas non. Ipsum ab illum dolorem.', null, '5','1');

INSERT INTO `messages_files` (`id`, `code`, `name`, `url`, `id_message_file`) VALUES ('1', 'sjeo', 'nihil', 'http://lockman.info/', '1');
INSERT INTO `messages_files` (`id`, `code`, `name`, `url`, `id_message_file`) VALUES ('2', 'auqj', 'quidem', 'http://www.howe.com/', '2');
INSERT INTO `messages_files` (`id`, `code`, `name`, `url`, `id_message_file`) VALUES ('3', 'rxht', 'unde', 'http://www.fadeljohns.info/', '3');
INSERT INTO `messages_files` (`id`, `code`, `name`, `url`, `id_message_file`) VALUES ('4', 'gusm', 'eum', 'http://feil.org/', '4');
INSERT INTO `messages_files` (`id`, `code`, `name`, `url`, `id_message_file`) VALUES ('5', 'lxje', 'harum', 'http://kessler.com/', '5');

INSERT INTO `roles` (`id`, `privileges`, `rol`) VALUES ('1', 'todos', 'Creador');
INSERT INTO `roles` (`id`, `privileges`, `rol`) VALUES ('2', 'lectura', 'Usuario');
INSERT INTO `roles` (`id`, `privileges`, `rol`) VALUES ('3', 'muchos', 'Moderador');


INSERT INTO `user_server_role` (`id`, `id_rol`, `id_server`, `id_user`) VALUES ('61', '1', '1', '1');
INSERT INTO `user_server_role` (`id`, `id_rol`, `id_server`, `id_user`) VALUES ('62', '2', '2', '2');
INSERT INTO `user_server_role` (`id`, `id_rol`, `id_server`, `id_user`) VALUES ('63', '3', '3', '3');
INSERT INTO `user_server_role` (`id`, `id_rol`, `id_server`, `id_user`) VALUES ('64', '2', '4', '4');
INSERT INTO `user_server_role` (`id`, `id_rol`, `id_server`, `id_user`) VALUES ('65', '2', '5', '5');


