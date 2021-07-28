<template>
    <div class="Lista">
       
            <Toolbar class="p-mb-4">
                <template #left>
                    <Button label="Novo" icon="pi pi-plus" class="p-button-success p-mr-2" @click="fazercadastro"  />
                    <Button label="Excluir" icon="pi pi-trash" class="p-button-danger" @click="deleteSelectedProducts" :disabled="!produtosSelecionados || !produtosSelecionados.length"  />                   
                </template>
                
            </Toolbar>
        
            <DataTable  :value= "produtos" :selection.sync="produtosSelecionados" dataKey= "id" :paginator="true" :rows="10" :filters="filters"
            paginatorTemplate="FirstPageLink PrevPageLink PageLinks NextPageLink LastPageLink CurrentPageReport RowsPerPageDropdown" :rowsPerPageOptions="[5,10,25]"
    currentPageReportTemplate="Mostrando {first} de {last} até {totalRecords} produtos">
                <template #header>
                    <div class="table-header">
                        <h5 class="p-m-0">Gerenciar Produto</h5>
                        <span class="p-input-icon-left">
                            <i class="pi pi-search" />
                            <InputText v-model="filters['global']" placeholder="Nome do Produto..." />
                        </span>
                    </div>
                </template>

                <Column selectionMode="multiple"  headerStyle="width: 3rem"></Column>
                <Column field="codigo" header="Código" sortable></Column>
                <Column field="nome" header="Nome" sortable></Column>
                <Column field="preco" header="Preço" sortable>
                    <template #body="slotProps">
                        {{formatCurrency(slotProps.data.preco)}}
                    </template>
                </Column>
                <Column field="categoria" header="Categoria" sortable></Column>
                <Column field="status" header="Status" sortable></Column>
                <Column>
                    <template #body="slotProps">
                        <Button icon="pi pi-pencil" class="p-button-rounded p-button-success p-mr-2" @click="abrirModal(slotProps.data)" />
                        <Button icon="pi pi-trash" class="p-button-rounded p-button-warning" @click="deletar(slotProps.data.id)" />
                    </template>                  
                </Column>
            </DataTable>

            <Dialog :visible.sync="modalCreate" :style="{width: '450px'}" header="Criar novo produto" :modal="true" class="p-fluid">
                <div class="p-inputtext-filled">
                    <label for="nome">Nome</label>
                    <InputText id="nome " v-model.trim="produto.nome" required="true" autofocus/>
                 </div>

                <div class="p-field">
                    <label for="descricao">Descrição</label>
                    <Textarea id="descricao" v-model="produto.descricao" required="true" rows="3" cols="52" />
                </div>
                <div class="p-field">
				<label for="statusinventorio" class="p-mb-3">Status do invetário</label>
				<Dropdown id="statusinventorio" v-model="selectedStatus" :options="statuses" optionLabel="code" placeholder="Selececione o Status">

				</Dropdown>
			</div>
                <div class="p-field">
                <label class="p-mb-3">Categoria</label>
                <div class="p-formgrid p-grid">
                    <div class="p-field-radiobutton p-col-6">
                        <RadioButton id="category1" name="category" value="CATEGORIA1" v-model="produto.categoria" />
                        <label for="category1">Categoria1</label>
                    </div>
                    <div class="p-field-radiobutton p-col-6">
                        <RadioButton id="category3" name="category" value="CATEGORIA2" v-model="produto.categoria" />
                        <label for="category3">Categoria2</label>
                    </div>
                    <div class="p-field-radiobutton p-col-6">
                        <RadioButton id="categoria3" name="categoria" value="CATEGORIA3" v-model="produto.categoria" />
                        <label for="categoria3">Categoria3</label>
                    </div>
                    <div class="p-field-radiobutton p-col-6">
                        <RadioButton id="categoria4" name="categoria" value="CATEGORIA4" v-model="produto.categoria" />
                        <label for="categoria4">Categoria4</label>
                    </div>
                </div>
            </div>
                <div class="p-formgrid p-grid">
                 <div class="p-field">
                    <label for="preco">Preço</label>
                    <InputText id="preco " v-model.trim="produto.preco" required="true" autofocus/>
                 </div>
                <div class="p-field">
                    <label for="qauntidade">Quantidade</label>
                    <InputText id="quantidade" v-model.trim="produto.quantidade" required="true" autofocus/>
                </div>
                <div class="p-field">
                    <label for="codigo">Código</label>
                    <InputText id="codigo" v-model.trim="produto.codigo" required="true" autofocus/>
                </div>
                </div>
                <template #footer>
                    <Button label="Cancelar" icon="pi pi-times" class="p-button-text" @click="modalCreate = false" />
                    <Button label="Salvar" icon="pi pi-check" class="p-button-text" @click="criarProduto"/>
                </template>                                                                 
            </Dialog>

            <Dialog  :visible.sync="modalUpdate" :style="{width: '450px'}" header="Detalhes do produto" :modal="true" class="p-fluid">
                <div class="p-inputtext-filled">
                    <label for="nome">Nome</label>
                    <InputText id="nome " v-model.trim="produto.nome" required="true" autofocus/>
                 </div>

                <div class="p-field">
                    <label for="descricao">Descrição</label>
                    <Textarea id="descricao" v-model="produto.descricao" :autoResize="true" required="true" rows="3" cols="52" />
                </div>

                <div class="p-field">
				<label for="statusinventorio" class="p-mb-3">Status do invetário</label>
				<Dropdown id="statusinventorio" v-model="selectedStatus" :options="statuses" optionLabel="code" placeholder="Selececione o Status">
				</Dropdown>
			</div>

                <div class="p-field">
                <label class="p-mb-3">Categoria</label>
                <div class="p-formgrid p-grid">
                    <div class="p-field-radiobutton p-col-6">
                        <RadioButton id="category1" name="category" value="CATEGORIA1" v-model="produto.categoria" />
                        <label for="category1">Categoria1</label>
                    </div>

                    <div class="p-field-radiobutton p-col-6">
                        <RadioButton id="category3" name="category" value="CATEGORIA2" v-model="produto.categoria" />
                        <label for="category3">Categoria2</label>
                    </div>

                    <div class="p-field-radiobutton p-col-6">
                        <RadioButton id="categoria3" name="categoria" value="CATEGORIA3" v-model="produto.categoria" />
                        <label for="categoria3">Categoria3</label>
                    </div>
                    <div class="p-field-radiobutton p-col-6">
                        <RadioButton id="categoria4" name="categoria" value="CATEGORIA4" v-model="produto.categoria" />
                        <label for="categoria4">Categoria4</label>
                    </div>
                </div>
            </div>
                <div class="p-formgrid p-grid">
                 <div class="p-field">
                    <label for="preco">Preço</label>
                    <InputText id="preco " v-model.trim="produto.preco" required="true" autofocus/>
                 </div>
                <div class="p-field">
                    <label for="qauntidade">Quantidade</label>
                    <InputText id="quantidade" v-model.trim="produto.quantidade" required="true" autofocus/>
                </div>
                <div class="p-field">
                    <label for="codigo">Código</label>
                    <InputText id="codigo" v-model.trim="produto.codigo" required="true" autofocus/>
                </div>
                </div>
                <template #footer>
                    <Button label="Cancelar" icon="pi pi-times" class="p-button-text" @click="modalUpdate = false" />
                    <Button label="Salvar" icon="pi pi-check" class="p-button-text" @click="atualizarProduto"/>
                </template>
            </Dialog>

            <Dialog :visible.sync="modalDeletar" :style="{width: '450px'}" header="Confirm" :modal="true">
                <div class="confirmation-content">
                    <i class="pi pi-exclamation-triangle p-mr-3" style="font-size: 2rem" />
                    <span >Tem certeza que desja deletar o produto?</span>
                </div>
                <template #footer>
                    <Button label="Não" icon="pi pi-times" class="p-button-text" @click="modalDeletar = false"/>
                    <Button label="Sim" icon="pi pi-check" class="p-button-text" @click="deletarProduto" />
                </template>
            </Dialog>


     </div>
 
    

</template>

<script>
import ProdutoDataService from "../Service/ProdutoDataService";


export default {
  
    data() {
        return {
            produtosSelecionados : null,
            produtos: [],
            filters: {},
            submitted: false,           
            localid:'',
            updateModal: false,
            modalCadastro: false,
            modalCreate: false,
            modalDeletar: false,
            modalUpdate:false,
            produto:{},
            selectedStatus: null,
            descricao:"olá",
            statuses: [
				{code: 'emestoque', value: 'EMESTOQUE'},
				{code: 'baixoestoque', value: 'BAIXOESTOQUE'},
				{code: 'semestoque', value: 'SEMESTOQUE'}
            ] 

        }
    },
    methods: {
        formatCurrency(value) {
            return value.toLocaleString('pt-BR', {style: 'currency', currency: 'BRL'});
        },
        fazercadastro(){
            this.modalCreate= !this.modalCreate;
            this.produto = {};
        },
        criarProduto() {
            var data = {
                nome: this.produto.nome,
                preco: this.produto.preco,
                codigo: this.produto.codigo,
                categoria: this.produto.categoria,
                status: this.selectedStatus.value,
                descricao: this.produto.descricao,
                quantidade: this.produto.quantidade
            }
                ProdutoDataService.create(data)                
                .then(response => {
                    this.produto.id = response.data.id
                    this.modalCreate = !this.modalCreate;
                    window.location.reload();
                })
                .catch( e => {
                    alert(e)
                })

        },
        abrirModal(data){
            this.modalUpdate = !this.modalUpdate
            this.produto = data;
        },      
        retrieveProdutos() {
             ProdutoDataService.getAll()
                .then(response => {
                    this.produtos = response.data            
                })
                .catch(e => {
                    alert(e)
                })
                
        },
        atualizarProduto() {
            this.modalUpdate = !this.modalUpdate;
            this.produto.status = this.selectedStatus.value;
            ProdutoDataService.update(this.produto.id, this.produto)
            .then(() => {
                console.log(this.produto);
            })
            .catch(e => {
            alert(e)
            });           

        },        
         deletar(id){
                this.modalDeletar = !this.modalDeletar
                this.localid = id;
        },

        deletarProduto(){          
            ProdutoDataService.delete(this.localid)
            .then(() => {
                window.location.reload();
            })
            .catch(e => {
               alert(e)
            });
            
        },
        deleteSelectedProducts() {
            this.produtos = this.produtos.filter(val => !this.produtosSelecionados.includes(val));
            this.produtosSelecionados = null;
        }
    
    },
    mounted() {
        this.retrieveProdutos();
    }
}
</script>

<style scoped>

.table-header {
    display: flex;
    align-items: center;
    justify-content: space-between;

}
.confirmation-content {
    display: flex;
    align-items: center;
    justify-content: center;
}

</style>


