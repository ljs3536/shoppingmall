package com.hertz.shoppingMall.utils.exception.image.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.hertz.shoppingMall.utils.image.model.Image;
import com.hertz.shoppingMall.utils.image.model.ImageType;
import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QImage is a Querydsl query type for Image
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QImage extends EntityPathBase<Image> {

    private static final long serialVersionUID = -1909922571L;

    public static final QImage image = new QImage("image");

    public final com.hertz.shoppingMall.config.jpa.QBaseDateEntity _super = new com.hertz.shoppingMall.config.jpa.QBaseDateEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDateTime = _super.createdDateTime;

    public final StringPath fileName = createString("fileName");

    public final StringPath filePath = createString("filePath");

    public final StringPath fileType = createString("fileType");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<ImageType> imageType = createEnum("imageType", ImageType.class);

    public final BooleanPath isMain = createBoolean("isMain");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDateTime = _super.modifiedDateTime;

    public final NumberPath<Long> referenceId = createNumber("referenceId", Long.class);

    public QImage(String variable) {
        super(Image.class, forVariable(variable));
    }

    public QImage(Path<? extends Image> path) {
        super(path.getType(), path.getMetadata());
    }

    public QImage(PathMetadata metadata) {
        super(Image.class, metadata);
    }

}

