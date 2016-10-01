package protocolsupport.protocol.packet.middleimpl.clientbound.play.v_1_4__1_5__1_6__1_7;

import java.io.IOException;

import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.packet.ClientBoundPacket;
import protocolsupport.protocol.packet.middle.clientbound.play.MiddleCustomPayload;
import protocolsupport.protocol.packet.middleimpl.PacketData;
import protocolsupport.protocol.serializer.RecyclableProtocolSupportPacketDataSerializer;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableSingletonList;

public class CustomPayload extends MiddleCustomPayload<RecyclableCollection<PacketData>> {

	@Override
	public RecyclableCollection<PacketData> toData(ProtocolVersion version) throws IOException {
		PacketData serializer = PacketData.create(ClientBoundPacket.PLAY_CUSTOM_PAYLOAD_ID, version);
		serializer.writeString(tag);
		if (tag.equals("MC|TrList")) {
			RecyclableProtocolSupportPacketDataSerializer tempdata = RecyclableProtocolSupportPacketDataSerializer.create(version);
			try {
				tempdata.writeMerchantData(data.readMerchantData());
				serializer.writeByteArray(tempdata);
			} finally {
				tempdata.release();
			}
		} else {
			serializer.writeByteArray(data);
		}
		return RecyclableSingletonList.create(serializer);
	}

}
