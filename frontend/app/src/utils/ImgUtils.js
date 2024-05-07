export default class ImgUtils {
    static CustomImage(brand) {
        let image = '';
        if(brand.toLowerCase().startsWith('bm')){
            image = 'bmw.png';
        } else if(brand.toLowerCase().startsWith('che')){
            image = 'chevrolet.svg';
        } else if(brand.toLowerCase().startsWith('fo')){
            image = 'ford.png';
        } else if(brand.toLowerCase().startsWith('hon')){
            image = 'honda.png';
        } else if(brand.toLowerCase().startsWith('ki')){
            image = 'kia.png';
        } else if(brand.toLowerCase().startsWith('merc')){
            image = 'mercedez.png';
        } else if(brand.toLowerCase().startsWith('nis')){
            image = 'nissan.png';
        } else if(brand.toLowerCase().startsWith('toy')){
            image = 'toyota.png';
        }
        return image;
    }
}
