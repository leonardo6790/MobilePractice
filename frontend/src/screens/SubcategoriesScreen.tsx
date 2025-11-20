import React,{useState,useEffect} from 'react';
import { View, Text, FlatList, TouchableOpacity, Alert, TextInput, Modal, ActivityIndicator, ScrollView } from 'react-native';
import { categoriesStyles } from "../styles/CategoriesStyles";
import { categoryService, authService } from '../services/api';

export default function CategoriesScreen() {
    const [categories, setCategories] = useState<any[]>([]);
    const [loading, setLoading] = useState(false);
    const [modalVisible, setModalVisible] = useState(false);
    const [editing, setEditing] = useState<any>(null);
    const [formData, setFormData] = useState({ name: '', description: '' });
    const [error, setError] = useState('');
    const [currentUser, setCurrentUser] = useState<any>(null);

    useEffect(() => {
        loadCurrentUser();
        loadCategories();
    }, []);

    const loadCurrentUser = async () => {
        try{
            const user = await authService.getCurrentUser();
            setCurrentUser(user); 
        } catch (error) {
            console.error('Error al cargar usuarios', error);

        }
    };

    const loadCategories = async () => {
        setLoading(true);
        setError('');
        try {
            const response = await categoryService.getAll();
            setCategories(response?.data || []);
        } catch (error) {
            setError('no je pudo cargaron las categorias');
            setCategories([]);
        } finally {
            setLoading(false);
        }
    };

    const handleSave = async () => {
        if (!formData.name.trim()) {
            Alert.alert('Error', 'El nombre es obligatorio');
            return;
        }
        try {
            if (editing) {
                await categoryService.update(editing.id, formData);
                Alert.alert('Éxito', 'Categoría actualizada exitosamente');
            } else {
                await categoryService.create(formData);
                Alert.alert('Éxito', 'Categoría creada exitosamente');
            }
            setModalVisible(false);
            resetForm();
            loadCategories();
        } catch (error) {
            Alert.alert('Error', 'No se pudo guardar la categoría');
        }   
    };

    const handleDelete = (item : any) =>{
        if(currentUser?.role !== 'ADMIN'){
            Alert.alert('Acceso Denegado sotsio', 'solo los administradores pueden eliminar categorias');
        }
    Alert.alert('Confirmar Eliminación', `¿Eliminar "${item.name}"?`, [
        { text: 'Cancelar', style: 'cancel' },
        {
            text: 'Eliminar',
            style: 'destructive',
            onPress: async () => {
                try {
                    await categoryService.delete(item.id);
                    Alert.alert('Éxito', 'Categoría eliminada exitosamente');
                    loadCategories();
                } catch (error) {
                    Alert.alert('Error', 'No se pudo eliminar la categoría');
                }
        }
    }
    ]);
    };

    const handleToggleActive =  (item : any) =>{
        const action = item.active ? 'Desactivar' : 'Activar';
        Alert.alert(`Confirmar`, `¿${action.charAt(0).toUpperCase() + action.slice(1)} "${item.name}"?`, [
            { text: 'Cancelar', style: 'cancel' },
            {
                text: action.charAt(0).toUpperCase() + action.slice(1), onPress: async () => {
                    try {
                        await categoryService.update(item.id, {
                            name: item.name,
                            description: item.description,
                            active: !item.active
                        });
                        Alert.alert('Éxito', `Categoría ${item.active ? 'desactivada' : 'activada'}`);
                        loadCategories();
                    } catch (error) {
                        Alert.alert('Error', `No se pudo ${action}`);
                    }
            }
        }
        ]);
    };

    const handleEdit = (item : any) =>{
        setFormData({ name: item.name, description: item.description || '' });
        setEditing(item);
        setModalVisible(true);
    };

    const resetForm = () => {
        setFormData({name: '', description: ''});
        setEditing(null);
    }

    const renderCategory = ({ item } : { item:any }) => (
        <View style={categoriesStyles.categoryCard}>
            <View style={categoriesStyles.categoryInfo}>
                <Text style={categoriesStyles.categoryName}>
                    {item.name} {!item.active && <Text style = {{color : '#999'}}> (Inactiva)</Text>}
                </Text>
                {item.description &&  (<Text style={categoriesStyles.categoryDescription}>{item.description}</Text>)}
            </View>
            <View style = {categoriesStyles.actionContainer}>
                <TouchableOpacity 
                style={[categoriesStyles.actionButton, categoriesStyles.editButton]}
                onPress={() => handleToggleActive(item)}
                >
                <Text style={[categoriesStyles.actionButtonText, categoriesStyles.editButtonText]}>Editar</Text>
            </TouchableOpacity>
            </View>
        </View>
    ) 

    const deleteCategory = ({ item } : { item:any }) => (
        <View style={categoriesStyles.categoryCard}>
            <View style={categoriesStyles.categoryInfo}>
                <Text style={categoriesStyles.categoryName}>
                    {item.name} {!item.active && <Text style = {{color : '#999'}}> (Inactiva)</Text>}
                </Text>
                {item.description &&  (<Text style={categoriesStyles.categoryDescription}>{item.description}</Text>)}
            </View>
            <View style = {categoriesStyles.actionContainer}>
                <TouchableOpacity 
                style={[categoriesStyles.actionButton, categoriesStyles.editButton]}
                onPress={() => handleToggleActive(item)}
                >
                <Text style={[categoriesStyles.actionButtonText, item.active ? categoriesStyles.deleteButtonText : categoriesStyles.editButtonText]}>
                    {item.active ? 'Desactivar' : 'Activar'}
                </Text>
            </TouchableOpacity>
            {currentUser?.role === 'ADMIN' && (
                <TouchableOpacity 
                style={[categoriesStyles.actionButton, categoriesStyles.deleteButton]}
                onPress={() => handleDelete(item)}
                >
                <Text style={[categoriesStyles.actionButtonText, categoriesStyles.deleteButtonText]}>Eliminar</Text>
            </TouchableOpacity>
            )}
            </View>
        </View>
    ); 

    if (loading) {
        return (
            <View style={categoriesStyles.loadingContainer}>
                <ActivityIndicator size="large" color="#007Aff"/>
                <Text style={categoriesStyles.loadingText}>Cargando categorías...</Text>
            </View>
        );
    }

    return (
        <View style={categoriesStyles.container}>
            <View style = {categoriesStyles.header}>
                <View style={categoriesStyles.headerContent}>
                    <Text style={categoriesStyles.headerTitle}>Categorías</Text>
                    <TouchableOpacity
                        style={categoriesStyles.addButton}
                        onPress={() => {
                            resetForm();
                            setModalVisible(true);
                        }}
                    >
                        <Text style={categoriesStyles.addButtonText}>+ Nueva</Text>
                    </TouchableOpacity>
                </View>
            </View>
            {error ? (
                <View style={categoriesStyles.errorContainer}>
                    <Text style={categoriesStyles.errorText}>{error}</Text>
                    <TouchableOpacity style={categoriesStyles.retryButton} onPress={loadCategories}>
                        <Text style={categoriesStyles.retryButtonText}>Reintentar</Text>
                    </TouchableOpacity>
                </View>
            ) : null}
            <FlatList
                data={categories}
                renderItem={renderCategory}
                keyExtractor={(item) => item.id?.toString() || ''}
                contentContainerStyle={categoriesStyles.listContent}
                showsVerticalScrollIndicator={false}
                listEmptyComponent={
                    !loading && !error ?(
                        <View style={categoriesStyles.emptyContainer}>
                            <Text style={categoriesStyles.emptyText}>No hay categorías disponibles.</Text>
                            <Text style={categoriesStyles.emptySubtext}>Toca "+ Nueva" para comenzar</Text>
                        </View>
                    )
                }
            />
                <Modal animationType="slide" transparent={true} visible={modalVisible}>
                    <View style={categoriesStyles.modelOverlay}>
                        <ScrollView>
                        <View style={categoriesStyles.modalHeader}>
                            <Text style={categoriesStyles.modalTitle}>
                                {editing ? 'Editar Categoría' : 'Nueva Categoría'}
                            </Text>
                        </View>
                        <View style= {categoriesStyles.formContainer}>
                            <View style={categoriesStyles.inputLabel}>
                                <TextInput style={categoriesStyles.input}
                                value={formData.name}
                                value={formData.name}
                                placeholder="Nombre de la categoría"
                                placeholderTextColor="#999"
                                />
                            </View>
                        </View>

                        <View style= {categoriesStyles.inputGroup}>
                                <Text style={categoriesStyles.inputLabel}>Description</Text>
                                <TextInput 
                                    style={[categoriesStyles.input, categoriesStyles.textArea]}
                                    value={formData.description}
                                    onChangeText={(text) => setFormData({...formData, description: text})}
                                    placeholder="Descripción de la categoría"
                                    placeholderTextColor="#999"
                                    multiline
                                    numberOfLines={3}
                                    textAlignVertical="top"
                                />
                        </View>
                    

                    <View style= {categoriesStyles.modalButtons}>
                                <TouchableOpacity>
                                    <Text style={[categoriesStyles.modalButton, categoriesStyles.cancelButton]}
                                    onPress={handleSave}>Cancelar</Text>
                                </TouchableOpacity>
                                <Text style={[categoriesStyles.modalButtonText, categoriesStyles.saveButtonText]}>{editing ? 'Actualizar' : 'Crear'}
                                    
                                </Text>
                        </View>
                        </ScrollView>
                       </View>
                   </View>
             </Modal>
        </View>
    ); 
}