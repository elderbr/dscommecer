[![DevSuperior](https://github.com/elderbr/assets/blob/main/DevSuperior/devSuperior_icon.png?raw=true)
](https://devsuperior.com.br/)

## Login e controle de acesso

---

### Visão geral do sistema

O sistema deve manter um cadastro de usuário, produtos e suas categorias. Cada
usuário possui nome, email, telefone, data de nascimento e uma senha de acesso. Os
dados dos produtos são: nome, descrição, preço e imagem. O sistema deve apresentar
um catálogo de produtos, os quais podem ser filtrados pelo nome do produto. A partir
desse catálogo, o usuário pode selecionar um produto para ver seus detalhes e para
decidir se o adiciona a um carrinho de compras. O usuário pode incluir e remover itens
do carrinho de compra, bem como alterar as quantidades de cada item. Uma vez que o
usuário decida encerrar o pedido, o pedido deve então ser salvo no sistema com o status
de "aguardando pagamento". Os dados de um pedido são: instante em que ele foi salvo,
status, e uma lista de itens, onde cada item se refere a um produto e sua quantidade no
pedido. O status de um pedido pode ser: aguardando pagamento, pago, enviado,
entregue e cancelado. Quando o usuário paga por um pedido, o instante do pagamento
deve ser registrado. Os usuários do sistema podem ser clientes ou administradores,
sendo que todo usuário cadastrado por padrão é cliente. Usuários não identificados
podem se cadastrar no sistema, navegar no catálogo de produtos e no carrinho de
compras. Clientes podem atualizar seu cadastro no sistema, registrar pedidos e visualizar
seus próprios pedidos. Usuários administradores tem acesso à área administrativa onde
pode acessar os cadastros de usuários, produtos e categorias.

---

### Modelo conceitual

Este é o modelo conceitual do sistema DSCommerce. Considerações:

- Cada item de pedido (OrderItem) corresponde a um produto no pedido, com uma
  quantidade. Sendo que o preço também é armazenado no item de pedido por
  questões de histórico (se o preço do produto mudar no futuro, o preço do item de
  pedido continua registrado com o preço real que foi vendido na época).
- Um usuário pode ter um ou mais "roles", que são os perfis de acesso deste usuário
  no sistema (client, admin).

---

### Casos de uso (visão geral)

O escopo funcional do sistema consiste nos seguintes casos de uso:
<div>
    <table>
        <thead>
            <tr>
                <th>Caso de uso</th>
                <th>Visão gera</th>
                <th>Acesso</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>Manter produtos</td>
                <td>CRUD de produtos, podendo filtrar itens pelo nome </td>
                <td>Somente Admin</td>
            </tr>
            <tr>
                <td>Manter categorias</td>
                <td>CRUD de categorias, podendo filtrar itens pelo nome</td>
                <td>Somente Admin</td>
            </tr>
            <tr>
                <td>Manter usuários</td>
                <td>CRUD de usuários, podendo filtrar itens pelo nome</td>
                <td>Somente Admin</td>
            </tr>
            <tr>
                <td>Gerenciar carrinho</td>
                <td>Incluir e remover itens do carrinho de compras, bem 
como alterar as quantidades do produto em cada 
item</td>
                <td>Público</td>
            </tr>
            <tr>
                <td>Consultar catálogo</td>
                <td>Listar produtos disponíveis, podendo filtrar produtos 
pelo nome</td>
                <td>Público</td>
            </tr>
            <tr>
                <td>Sign up</td>
                <td>Cadastrar-se no sistema</td>
                <td>Público</td>
            </tr>
            <tr>
                <td>Login</td>
                <td>Efetuar login no sistema</td>
                <td>Público</td>
            </tr>
            <tr>
                <td>Registrar pedido</td>
                <td>Salvar no sistema um pedido a partir dos dados do 
carrinho de compras informado</td>
                <td>Usuário logado</td>
            </tr>
            <tr>
                <td>Atualizar perfil</td>
                <td>Atualizar o próprio cadastro</td>
                <td>Usuário logado</td>
            </tr>
            <tr>
                <td>Visualizar pedidos</td>
                <td>Visualizar os pedidos que o próprio usuário já fez</td>
                <td>Usuário logado</td>
            </tr>
            <tr>
                <td>Registrar pagamento</td>
                <td>Salvar no sistema os dados do pagamento de um 
pedido</td>
                <td>Somente Admin</td>
            </tr>
            <tr>
                <td>Reportar pedidos</td>
                <td>Relatório de pedidos, podendo ser filtrados por data</td>
                <td>Somente Admin</td>
            </tr>
        </tbody>
    </table>
</div>